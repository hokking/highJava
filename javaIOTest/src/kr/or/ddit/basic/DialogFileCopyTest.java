package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

public class DialogFileCopyTest {
	enum DialogChoice {OPEN, SAVE};

	public static void main(String[] args) {
		new DialogFileCopyTest().fileCopyStart();

	}

	public void fileCopyStart() {
		File file = showDialog(DialogChoice.OPEN);

		if (file == null) {
			System.out.println("복사할 원본 파일을 선택하지 않았습니다.");
			System.out.println("작업을 중단합니다");
			return;
		}
		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다");
			System.out.println("작업을 중단합니다");
			return;
		}

		File targetFile = showDialog(DialogChoice.SAVE);

		if (targetFile == null) {
			System.out.println("대상파일을 선택하지 않았습니다.");
			System.out.println("작업을 중단합니다");
			return;
		}

		if (!targetFile.exists()) { // '연습용'폴더가 없으면
			targetFile.mkdir(); // '연습용'폴더를 만든다
		}

		try {// 복사할 원본 파일에 사용할 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);

			FileOutputStream fout = new FileOutputStream(
					targetFile.getAbsoluteFile());
			// new FileOutputStream(targetFile);

			System.out.println("복사시작");

			int c;

			while ((c = fin.read()) != -1) {
				fout.write((char) c);
			}

			fin.close();
			fout.close();
			System.out.println("복사 완료");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
