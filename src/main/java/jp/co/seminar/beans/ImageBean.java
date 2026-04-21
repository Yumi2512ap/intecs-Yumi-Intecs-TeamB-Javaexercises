package jp.co.seminar.beans;

import java.io.Serializable;
import java.security.Timestamp;

public class ImageBean implements Serializable {
	private int imageId;
	private String roomId;
	private String imageName;
	private String imageType;
	private byte[] imageContent;
	private int imageSize;
	private Timestamp create_At;

	public int getImage_id() {
		return imageId;
	}

	public String getRoomId() {
		return roomId;
	}

	public String getImageName() {
		return imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public byte[] getImageContent() {
		return imageContent;
	}

	public int getImage_size() {
		return imageSize;
	}

	public Timestamp getCreate_At() {
		return create_At;
	}
	@Override
	public String toString() {
		return "ImageBean[";
	}
}
