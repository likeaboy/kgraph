package org.jrocky.kgraph.internal;

public class KuduObjectProperty {
	private String propertyName;
	private String propertyUri;
	private String propertyValue;
	private String valueType;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyUri() {
		return propertyUri;
	}
	public void setPropertyUri(String propertyUri) {
		this.propertyUri = propertyUri;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
}
