package org.jrocky.kgraph.internal;

import java.util.ArrayList;
import java.util.List;

public class KuduNode {
	private String beginTime;
	private String createTime;
	private String creator;
	private String endTime;
	private long latitude;
	private long longitude;
	private String objectIcon;
	private String objectId;
	private String objectName;
	private String objectType;
	private String objectUri;
	private List<KuduObjectProperty> properties = new ArrayList<KuduObjectProperty>();
	private String typeLabel;
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public String getObjectIcon() {
		return objectIcon;
	}
	public void setObjectIcon(String objectIcon) {
		this.objectIcon = objectIcon;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getObjectUri() {
		return objectUri;
	}
	public void setObjectUri(String objectUri) {
		this.objectUri = objectUri;
	}
	public List<KuduObjectProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<KuduObjectProperty> properties) {
		this.properties = properties;
	}
	public String getTypeLabel() {
		return typeLabel;
	}
	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}
}
