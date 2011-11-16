package com.krikelin.spotify.watcher;

import android.graphics.drawable.Drawable;

public class ListEntry {
	public ListEntry(){
		
	}
	public ListEntry(String title,String text,OnClickListener onClickHandler){
		this.title=title;
		this.description=text;
		this.onClickHandler=onClickHandler;
		
	}
	public ListEntry(String title,String text,Drawable icon,OnClickListener onClickHandler){
		this.title=title;
		this.description=text;
		this.onClickHandler=onClickHandler;
		this.icon = icon;
	}
	public ListEntry(boolean enabled, String title,String text,OnClickListener onClickHandler){
		this.title=title;
		this.enabled = enabled;
		this.description=text;
		this.onClickHandler=onClickHandler;
		
	}
	public ListEntry(boolean enabled, String title,String text,Drawable icon,OnClickListener onClickHandler){
		this.title=title;
		this.enabled = enabled;
		this.description=text;
		this.onClickHandler=onClickHandler;
		this.icon = icon;
	}
	public static final int TYPE_ITEM = 0;
	public static final int TYPE_SEPARATOR = 1;
	private int type;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	private boolean enabled = true;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * On click listener for an list entry
	 * @author Alexander
	 *
	 */
	public interface OnClickListener{
		public void onClick(ListEntry sender);
	}
	private Drawable icon;
	private String title = "";
	private String description = ""; 
	private OnClickListener onClickHandler;
	private Object tag;
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OnClickListener getOnClickHandler() {
		return onClickHandler;
	}
	public void setOnClickHandler(OnClickListener onClickHandler) {
		this.onClickHandler = onClickHandler;
	}
	public Object getTag() {
		return tag;
	}
	public void setTag(Object tag) {
		this.tag = tag;
	}
	 
	
}
