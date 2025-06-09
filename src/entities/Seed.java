package entities;

import core.Configs;
import core.Game;
import core.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Seed extends Item {

    private int sSize = Configs.SEED_SIZE;
    private int sOff = Configs.SEED_OFFSET;
    protected Random randomGenerator;

    private float hueOffset = 0.0f;
    private int colorIndex = 0;
    private int frameCounter = 0;
    private static final int CHANGE_INTERVAL = 15;

    protected StringBuilder sb;
    protected String freeString;

    public String name;
    public int cost;
    public Map<String, Boolean> activeMutations;
    private ArrayList<String> currentMutations;

    protected boolean hasMutation, isGradient;
    protected Color shockedPrimary, shockedSecondary;
    protected String currentColoredMutation;

    public Seed(
            String name, int cost, boolean isGradient,
            Color shockedPrimary, Color shockedSecondary,
            BufferedImage sprite
    ) {
        this.randomGenerator = new Random();
        this.activeMutations = new HashMap<>();
        this.currentMutations = new ArrayList<>();
        this.sb  = new StringBuilder();

        this.ewidth = sSize;
        this.eheight = sSize;
        this.currentColoredMutation = "";
        this.freeString = "";

        this.name = name;
        this.cost = cost;
        this.isGradient = isGradient;
        this.shockedPrimary = shockedPrimary;
        this.shockedSecondary = shockedSecondary;
        this.sprite = sprite;

        initializeMutations();
        attemptRandomMutation();
        attemptRandomMutation();
        attemptRandomMutation();
        attemptRandomMutation();
    }

    private void initializeMutations() {

        for(int i = 0; i < Configs.MUTATION_NAMES.length; i++){
            activeMutations.put(Configs.MUTATION_NAMES[i], false);
        }
    }

    protected void attemptRandomMutation() {
        // Higher chance of NO mutation
        double noMutationChance = 0.50; // 50% chance of NO mutation
        if (randomGenerator.nextDouble() < noMutationChance) {
//             System.out.println(this.name + " got no mutation.");
            return; // Exit without applying any mutation
        }

        // If we proceed, there's a (1 - noMutationChance) to get *some* mutation.
        // Now, distribute this remaining probability among the tiers.

        // Tier definitions by index ranges
        // Tier 1 (Common): 0-4 (5 mutations) - Weight 10
        // Tier 2: 5-10 (6 mutations) - Weight 8
        // Tier 3: 11-14 (4 mutations) - Weight 6
        // Tier 4: 15-17 (3 mutations) - Weight 4
        // Tier 5: 18-20 (3 mutations) - Weight 2
        // Tier 6 (Rare): 21-23 (3 mutations) - Weight 1
        // (Adjust these indices and weights based on the final ALL_MUTATION_NAMES list)

        int[] tierWeights = {10, 8, 6, 4, 2, 1}; // Weights for Tier 1 to Tier 6
        int[] tierStartIndex = {0, 5, 11, 15, 18, 21}; // Start index in ALL_MUTATION_NAMES for each tier
        int[] tierMutationCount = {5, 6, 3, 3, 3, 3}; // Number of mutations in each tier

        int totalWeight = 0;
        for (int weight : tierWeights) {
            totalWeight += weight;
        }

        int randomPick = randomGenerator.nextInt(totalWeight);
        int cumulativeWeight = 0;
        int selectedTierIndex = -1; // 0 for Tier 1, 1 for Tier 2, etc.

        for (int i = 0; i < tierWeights.length; i++) {
            cumulativeWeight += tierWeights[i];
            if (randomPick < cumulativeWeight) {
                selectedTierIndex = i;
                break;
            }
        }

        if (selectedTierIndex != -1) {
            int baseIndexOfTier = tierStartIndex[selectedTierIndex];
            int mutationsInTier = tierMutationCount[selectedTierIndex];
            int mutationOffsetInTier = randomGenerator.nextInt(mutationsInTier);
            int finalMutationIndexInAllNames = baseIndexOfTier + mutationOffsetInTier;

//            System.out.println(mutationNames[finalMutationIndexInAllNames]);

            applyMutation(Configs.MUTATION_NAMES[finalMutationIndexInAllNames]);
        }
    }

    private void applyMutation(String mutationName) {
        activeMutations.put(mutationName, true);
        hasMutation = true;
//        System.out.println(this.name + " mutated into " + mutationName);
        currentMutations.add(mutationName);
        mutateSprite(mutationName);
    }

    private void gradientMutation(
            Color color1, Color color2, Color shocked1, Color shocked2
    ){
        sprite = Tools.gradientImage(
                sprite, color1, color2
        );

        //override shocked colors, and make non gradient seeds gradient
        isGradient = true;
        shockedPrimary = shocked1;
        shockedSecondary = shocked2;
    }

    private void mutateSprite(String mutationName){

        switch (mutationName) {
            case "candy" -> gradientMutation(
                    Configs.CANDY_1, Configs.CANDY_2,
                    Configs.SHOCKED_RED_1, Configs.SHOCKED_WHITE_1
            );
            case "galactic" -> gradientMutation(
                    Configs.GALACTIC_1, Configs.GALACTIC_2,
                    Configs.SHOCKED_BLUE_1, Configs.SHOCKED_PINK_1
            );
            case "magical" -> gradientMutation(
                    Configs.MAGICAL_1, Configs.MAGICAL_2,
                    Configs.SHOCKED_BLUE_1, Configs.SHOCKED_WHITE_1
            );
        }

        if(mutationName.equals("energized")){
            if(!currentColoredMutation.isEmpty()){
                sprite = Tools.tintImage(
                        sprite,
                        Configs.SHOCKED_COLORS_MAP.get(currentColoredMutation)
                );
            }
            else if(isGradient){
                sprite = Tools.gradientImage(
                        sprite, shockedPrimary, shockedSecondary
                    );
            }
            else sprite = Tools.tintImage(sprite, shockedPrimary);
        }

        if(!Configs.COLORS_MAP.containsKey(mutationName)) return;

        sprite = Tools.tintImage(sprite, Configs.COLORS_MAP.get(mutationName));
        currentColoredMutation = mutationName;
    }

    private void renderMutationEffects(Graphics2D g2){

        //any dripping mutation
        for(int i = 0; i < Configs.DRIPPING_MUTATIONS.length; i++){
            if(activeMutations.get(Configs.DRIPPING_MUTATIONS[i])){
                //render sparkles
                break;
            }
        }

        //any fume mutation
        for(int i = 0; i < Configs.FUME_MUTATION.length; i++){
            if(activeMutations.get(Configs.FUME_MUTATION[i])){
                //render sparkles
                break;
            }
        }

        //shamrock
        if(activeMutations.get("lucky")){

        }

        //explosive
        if(activeMutations.get("explosive")){

        }

        //any sparkle mutation
        for(int i = 0; i < Configs.SPARKLE_MUTATIONS.length; i++){
            if(activeMutations.get(Configs.SPARKLE_MUTATIONS[i])){
                //render sparkles
                break;
            }
        }

        //colorful
        if(activeMutations.get("colorful")){
            renderColorfulMutation();
        }
        //party
        else if(activeMutations.get("party")){
            renderPartyMutation();
        }

        //ice (must be moist first AND NOT be cold)
        if(activeMutations.get("ice") && activeMutations.get("moist")
                && !activeMutations.get("cold")){
            activeMutations.put("moist", false);
            g2.setColor(Configs.ICE_1);
            g2.fillRect(sx - et, sy - et, h, h);
        }

    }

    private void renderColorfulMutation() {

        hueOffset += 0.005f;

        if (hueOffset > 1.0f) {
            hueOffset -= 1.0f;
        }

        Color startColor = Color.getHSBColor(hueOffset, 1.0f, 1.0f);

        float endHue = hueOffset + 0.2f;
        if (endHue > 1.0f) {
            endHue -= 1.0f;
        }
        Color endColor = Color.getHSBColor(endHue, 1.0f, 1.0f);

        sprite = Tools.gradientImage(sprite, startColor, endColor);
    }

    private void renderPartyMutation() {

        frameCounter++;

        if (frameCounter >= CHANGE_INTERVAL) {

            frameCounter = 0;

            colorIndex++;

            if (colorIndex >= Configs.PARTY_COLORS.length) {
                colorIndex = 0;
            }

            Color newColor = Configs.PARTY_COLORS[colorIndex];
            sprite = Tools.tintImage(sprite, newColor);
        }
    }

    @Override
    public void render(Graphics2D g2) {

        if (hide) return;

        int px = (int) Game.player.x;
        int py = (int) Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        sx = (int) (x - px + psx);
        sy = (int) (y - py + psy);

        sx -= sOff;
        sy -= sOff;

        boolean isHorizontallyVisible = (x + ts > px - psx) && (x < px - psx + Configs.SCREEN_WIDTH);
        boolean isVerticallyVisible = (y + ts > py - psy) && (y < py - psy + Configs.SCREEN_HEIGHT);

        if (isHorizontallyVisible && isVerticallyVisible) {
            g2.drawImage(sprite, sx, sy, ewidth, eheight, null);

            //render effects
            renderMutationEffects(g2);

            //Debug draw item's bounding box on screen
            g2.setColor(Color.ORANGE);
            g2.drawRect(sx, sy, ewidth, eheight);
        }
    }

    public ArrayList<String> getActiveMutationDisplayStrings() {

        ArrayList<String> displayStrings = new ArrayList<>();

        if (currentMutations != null && !currentMutations.isEmpty()) {
            for (String mutationName : currentMutations) {
                int multiplier = Configs.MULTIPLIERS_MAP.getOrDefault(mutationName, 1);
                displayStrings.add(mutationName + " (x" + multiplier + ")");
            }
        }
        return displayStrings;
    }

    @Override
    public String toString() {

        sb.append(this.name != null ? this.name : "Unknown Seed");

        ArrayList<String> mutationDisplays = getActiveMutationDisplayStrings();

        if (!mutationDisplays.isEmpty()) {
            sb.append(System.lineSeparator()); // Conceptually a second line
            boolean first = true;
            for (String display : mutationDisplays) {
                if (!first) {
                    sb.append(" + ");
                }
                sb.append(display);
                first = false;
            }
        }
        return sb.toString();
    }

}