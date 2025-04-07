package pantrypal.inventory;


/**
 * Enum representing various ingredient categories.
 * Categories such as dairy, spice, vegetable, fruit, and others are included to help organize ingredients.
 */
public enum Category {
    DAIRY,
    SPICE,
    VEGETABLE,
    FRUIT,
    POULTRY,
    GRAIN,
    CONDIMENT,
    MISC,
    NUTS;

    /**
     * Returns a user-friendly version of the category name, where the first letter is capitalized
     * and the rest of the letters are in lowercase.
     *
     * @return A formatted category name.
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    /**
     * Parses a string into the corresponding Category enum.
     * If the input text is invalid or empty, an exception is thrown.
     *
     * @param text The string to parse.
     * @return The corresponding Category enum.
     * @throws IllegalArgumentException If the input string does not match a valid category.
     */
    public static Category parseCategory(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }

        for (Category category : Category.values()) {
            if (category.name().equalsIgnoreCase(text)) {
                return category;
            }
        }

        throw new IllegalArgumentException("Invalid category: " + text + "\nType categoryList for a list of " +
                "valid categories.");
    }

}
