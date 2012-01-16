package org.exoplatform.service;

import java.util.List;

import org.exoplatform.model.Picture;

public interface ContentService {
	public String getProductThumbnailPath(int productId);
	
	public List<Picture> getProductPictures(int productId);
}
