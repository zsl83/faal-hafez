import com.fasterxml.jackson.annotation.JsonProperty;

public class Faal {
    @JsonProperty("Poem")
    private String poem;

    @JsonProperty("Interpretation")
    private String interpretation;

    // Getters and Setters
    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}


