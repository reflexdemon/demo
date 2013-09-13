package demo.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DemoVO.
 */
public class DemoVO implements Serializable {

    /** The fahrenheit. */
    @JsonProperty("fahrenheit")
    private float fahrenheit;
    
    /** The celsius. */
    @JsonProperty("celsius")
    private float celsius;

    /**
     * @return the fahrenheit
     */
    public float getFahrenheit() {
	return fahrenheit;
    }

    /**
     * @param fahrenheit
     *            the fahrenheit to set
     */
    public void setFahrenheit(float fahrenheit) {
	this.fahrenheit = fahrenheit;
    }

    /**
     * @return the celsius
     */
    public float getCelsius() {
	return celsius;
    }

    /**
     * @param celsius
     *            the celsius to set
     */
    public void setCelsius(float celsius) {
	this.celsius = celsius;
    }

}
