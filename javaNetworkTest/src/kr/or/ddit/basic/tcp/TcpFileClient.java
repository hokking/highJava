package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

public class TcpFileClient {
	enum DialogChoice {
		OPEN, SAVE
	};

	private void clientStart() {

		// File file = new File("d:/d_other/펭귄.jpg");
		File file = showDialog(DialogChoice.OPEN);

		String fileName = file.getName();

		if (file == null) {
			System.out.println("전송할 파일을 선택하지 않았습니다.");
			System.out.println("작업을 중단합니다...");
		}
		if (!file.exists()) {
			System.out.println("파일이 없습니다. 중단합니다");
			return;
		}

		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작");
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			BufferedOutputStream bos = new BufferedOutputStream(dos);

			// 서버에 접속하면 첫번째로 전송할 파일의 파일명을 전송한다.
			dos.writeUTF(fileName);
			
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));


			byte[] temp = new byte[1024];
			int length = 0;

			while ((length = bis.read(temp)) > 0) {
				bos.write(temp, 0, length);
			}

			bos.flush();

			System.out.println("파일전송 완료");
			
			dos.close();
			bis.close();
			bos.close();
			socket.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("파일 전송 실패");
		}
	}

	public File showDialog(DialogChoice choice) {
		JFileChooser chooser = new JFileChooser();

		// FileNameExtensionFilter doc = new
		// FileNameExtensionFilter("Word File",
		// "docx", "doc");
		// FileNameExtensionFilter img = new
		// FileNameExtensionFilter("Image File",
		// new String[] { "png", "jpg", "gif" });
		// FileNameExtensionFilter txt = new FileNameExtensionFilter(
		// "Text File(.txt)", "txt");
		//
		// chooser.addChoosableFileFilter(doc);
		// chooser.addChoosableFileFilter(img);
		// chooser.addChoosableFileFilter(txt);

		// chooser.setFileFilter(txt);

		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setCurrentDirectory(new File("d:/d_other"));
		int result = -1;
		if (choice == DialogChoice.OPEN) {
			result = chooser.showOpenDialog(new Panel());
		} else if (choice == DialogChoice.SAVE) {
			result = chooser.showSaveDialog(new Panel());
		}
		File selectedFile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			// 선택한 파일 정구구하기
			selectedFile = chooser.getSelectedFile();
		}
		return selectedFile;

	}

	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

}
