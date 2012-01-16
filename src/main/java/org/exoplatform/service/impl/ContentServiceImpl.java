package org.exoplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;

import org.exoplatform.model.Picture;
import org.exoplatform.service.ContentService;
import org.exoplatform.services.wcm.publication.WCMComposer;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;
import org.exoplatform.wcm.webui.Utils;

public class ContentServiceImpl implements ContentService {

	private String repository;
	private String workspace;
	private String path;

	public ContentServiceImpl(String contentsRootPath) {
		super();

		String[] contentsRootPathElements = contentsRootPath.split(":");
		repository = contentsRootPathElements[0];
		workspace = contentsRootPathElements[1];
		path = contentsRootPathElements[2];
		if (!path.endsWith("/")) {
			path += "/";
		}
	}

	@Override
	public String getProductThumbnailPath(int productId) {
		String thumbnailPath = null;

		// get wcmcomposer service
		WCMComposer wcmComposer = WCMCoreUtils.getService(WCMComposer.class);

		HashMap<String, String> filters = new HashMap<String, String>();
		//filters.put(WCMComposer.FILTER_LANGUAGE, Util.getPortalRequestContext().getLocale().getLanguage());
		filters.put(WCMComposer.FILTER_MODE, Utils.getCurrentMode());
		// take the last published version
		filters.put(WCMComposer.FILTER_VERSION, null);

		try {
			Node productThumbnailNode = wcmComposer.getContent(workspace, path + productId + "/thumbnail", filters,
					WCMCoreUtils.getUserSessionProvider());
			if (productThumbnailNode != null) {
				thumbnailPath = path + productId + "/thumbnail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			thumbnailPath = null;
		}

		return thumbnailPath;
	}

	@Override
	public List<Picture> getProductPictures(int productId) {
		List<Picture> pictures = null;

		// get wcmcomposer service
		WCMComposer wcmComposer = WCMCoreUtils.getService(WCMComposer.class);

		HashMap<String, String> filters = new HashMap<String, String>();
		// content of the currently selected language
		//filters.put(WCMComposer.FILTER_LANGUAGE, Util.getPortalRequestContext().getLocale().getLanguage());
		// live or edit mode (will respectively get draft or published content)
		filters.put(WCMComposer.FILTER_MODE, Utils.getCurrentMode());
		filters.put(WCMComposer.FILTER_VERSION, WCMComposer.BASE_VERSION);
		// order clauses
		filters.put(WCMComposer.FILTER_ORDER_BY, "exo:dateModified");
		filters.put(WCMComposer.FILTER_ORDER_TYPE, "ASC");

		try {
			// service call to retrieve the contents
			List<Node> picturesNodes = wcmComposer.getContents(workspace, path + productId, filters,
					WCMCoreUtils.getUserSessionProvider());
			pictures = new ArrayList<Picture>();
			for (Node pictureNode : picturesNodes) {
				// exclude thumbnail
				if (!pictureNode.getProperty("exo:name").getString().equals("thumbnail")) {
					
					// In live mode, the last published version is a frozenNode, so
					// we need to get the node referenced by this frozen to get the real path
					String picturePath = null;
					if (pictureNode.isNodeType("nt:frozenNode")) {
						String uuid = pictureNode.getProperty("jcr:frozenUuid").getString();
						Node originalNode = pictureNode.getSession().getNodeByUUID(uuid);
						picturePath = originalNode.getPath();
					} else {
						picturePath = pictureNode.getPath();
					}
					
					Picture picture = new Picture(pictureNode.getProperty("exo:name").getString(), picturePath, pictureNode.getProperty("exo:title").getString());
					pictures.add(picture);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			pictures = null;
		}

		return pictures;
	}

}
