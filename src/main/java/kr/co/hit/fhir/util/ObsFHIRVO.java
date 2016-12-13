package kr.co.hit.fhir.util;

import java.util.List;
import java.util.Map;

public class ObsFHIRVO {

	private String id;
	private Map<String, Object> meta;
	private Map<String, Object> subject;
	private String effectiveDateTime;
	private Map<String, Object> bodySite;
	private List<Object> component;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Object> getMeta() {
		return meta;
	}
	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}
	public Map<String, Object> getSubject() {
		return subject;
	}
	public void setSubject(Map<String, Object> subject) {
		this.subject = subject;
	}
	public String getEffectiveDateTime() {
		return effectiveDateTime;
	}
	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}
	public Map<String, Object> getBodySite() {
		return bodySite;
	}
	public void setBodySite(Map<String, Object> bodySite) {
		this.bodySite = bodySite;
	}
	public List<Object> getComponent() {
		return component;
	}
	public void setComponent(List<Object> component) {
		this.component = component;
	}
}
