package jp.co.seminar.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImageBean implements Serializable {
	private String imageId;
	private String roomId;
	private String imageName;
	private String imageType;
	private byte[] imageContent;
	private int imageSize;
	private Timestamp createdAt;

	public ImageBean() {
	}

	public ImageBean(String imageId, String roomId, String imageName, String imageType,
			byte[] imageContent, int imageSize, Timestamp createdAt) {
		this.imageId = imageId;
		this.roomId = roomId;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageContent = imageContent;
		this.imageSize = imageSize;
		this.createdAt = createdAt;
	}

	public String getImageId() {
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

	public int getImageSize() {
		return imageSize;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "ImageBean[imageId" + imageId + ",roomId" + roomId
				+ ",imageName" + imageName + ",imageType" + imageType
				+ ",cereatedAt" + createdAt;
	}
}
