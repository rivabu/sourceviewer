package com.rients.org.sourceviewer.executables;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.rients.org.sourceviewer.domain.FileContent;
import com.rients.org.sourceviewer.domain.Tree;
import com.rients.org.sourceviewer.domain.TreeElement;
import com.rients.org.sourceviewer.domain.Type;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class FileUploader {
	String root = null;
	List<String> binaryExtensions = null;

	public FileUploader(String root) {
		super();
		this.root = root;
	}

	public void uploadFiles(Tree tree, int projectId) throws IOException {
		if (binaryExtensions == null) {
			loadBinaryExtensions();
		}
		RestClient restClient = new RestClient();

		for (TreeElement elem : tree.getElements()) {
			if (elem.getType().equals(Type.node)) {
				FileContent fileContent = new FileContent();
				fileContent.setProjectId(projectId);
				fileContent.setName(elem.getName());
				File file = new File(elem.getFileId());
				System.out.println("uploading: " + elem.getFileId());
				if (isBinary(elem.getExtension())) {
					BufferedImage img = ImageIO.read(file);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ImageIO.write(img, elem.getExtension(), bos);
					byte[] imageBytes = bos.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String imageString = encoder.encode(imageBytes);
					bos.close();
					fileContent.setEncodedContent(imageString);
					fileContent.setBinary(true);

				} else {
					String content = FileUtils.readFileToString(file);
					byte[] encoded = Base64.encodeBase64(content.getBytes());
					fileContent.setEncodedContent(new String(encoded));
					fileContent.setBinary(false);
				}
				//System.out.println("Base64 Encoded String : " + fileContent.getEncodedContent());
				String fileId = restClient.store(fileContent);
				elem.setFileId(fileId);
			}
		}
	}

	private boolean isBinary(String extension) {
		boolean returnValue = false;
		for (String binaryExtension : binaryExtensions) {
			if (binaryExtension.equals(extension)) {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}

	public void loadBinaryExtensions() throws IOException {
		binaryExtensions = FileUtils.readLines(new File(root + "//binaryextensions.txt"));
	}

}
