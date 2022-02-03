/**
 * this class hold a token location in file
 */
class tokenLocation {
    private final int lineOffset;
    private final int charOffset;

    /**
     * @param lineOffset relative line offset to the entire file
     * @param charOffset relative char offset to the lineOffset
     */
    public tokenLocation(int lineOffset, int charOffset){
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    /**
     * @return  {@link #tokenLocation} as a string
     */
    @Override
    public String toString() {
        return "[" +
                "lineOffset=" + lineOffset +
                ", charOffset=" + charOffset +
                "]";
    }
}