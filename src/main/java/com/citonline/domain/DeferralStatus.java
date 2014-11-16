package com.citonline.domain;

public enum DeferralStatus 
{
	UPLOADED,APPROVED, DELETED,DOWNLOADED_BY_LECTURER, SIGNED_BY_LECUTRER,
	DOWNLOADED_CORD, SIGNED_CORD;
	
	public int getStatus() {return ordinal() +1;}
}
