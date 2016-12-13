package kr.co.hit.lvlab.mob.sample.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObservationSampleParser {
	
	private String resourceType;
	private String lastUpdated;
	private String reference;
	private String effectiveDateTime;
	private String bodySite;
	private String sbp;
	private String dbp;
	
	Map<String, Object> map = new HashMap<String, Object>();
		
	public ObservationSampleParser(Map<String, Object> map){
		this.map = map;
	}

	public String getResourceType() {
		return (String) map.get("resourceType");
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getLastUpdated() {
		return (String) ((Map) map.get("meta")).get("lastUpdated");
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getReference() {
		return (String) ((Map) map.get("subject")).get("reference");
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEffectiveDateTime() {
		return (String) map.get("effectiveDateTime");
	}

	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public String getBodySite() {
		return (String) ((Map) ((List) ((Map) map.get("bodySite")).get("coding")).get(0)).get("display");
	}

	public void setBodySite(String bodySite) {
		this.bodySite = bodySite;
	}

	public String getSbp() {
		return String.valueOf(((Map) ((Map) ((List) map.get("component")).get(0)).get("valueQuantity")).get("value"))
				+ " "
				+ String.valueOf(((Map) ((Map) ((List) map.get("component")).get(0)).get("valueQuantity")).get("unit"));
	}

	public void setSbp(String sbp) {
		this.sbp = sbp;
	}

	public String getDbp() {
		return String.valueOf(((Map) ((Map) ((List) map.get("component")).get(1)).get("valueQuantity")).get("value"))
				+ " "
				+ String.valueOf(((Map) ((Map) ((List) map.get("component")).get(1)).get("valueQuantity")).get("unit"));
	}

	public void setDbp(String dbp) {
		this.dbp = dbp;
	}
}
