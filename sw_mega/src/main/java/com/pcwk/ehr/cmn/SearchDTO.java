package com.pcwk.ehr.cmn;

public class SearchDTO extends DTO {

	private String searchDiv; //검색 구분
	
	private String searchWord; //검색어
	
	public SearchDTO() {
	}

	/**
	 * @param searchDiv
	 * @param searchWord
	 */
	public SearchDTO(String searchDiv, String searchWord) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	private int noCode;
	private int fbCode;

	public void setFbCode(int fbCode) {
		this.fbCode = fbCode;
	}

	public int getNoCode() {
	    return noCode;
	}

	public void setNoCode(int noCode) {
	    this.noCode = noCode;
	}

	/**
	 * @return the searchDiv
	 */
	public String getSearchDiv() {
		return searchDiv;
	}

	/**
	 * @param searchDiv the searchDiv to set
	 */
	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}

	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "SearchDTO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + "]";
	}



}
