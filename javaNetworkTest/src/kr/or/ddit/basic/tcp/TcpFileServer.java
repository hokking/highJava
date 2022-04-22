package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {

	public static void main(String[] args) {

		File targetFile = new File("d:/d_other/upload");

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비중입니다...");
			Socket socket = server.accept();

			System.out.println("파일 저장 시작");
			
			// 소켓용 입력 스트림 객체 생성
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			BufferedInputStream bis = new BufferedInputStream(dis);

			// 클라이언트가 접속되었을 때 첫번째로 보내온 파일이름을 받는다
			String fileName = dis.readUTF();
			
//			File saveFile = new File(targetFile, "펭귄.jpg");
			File saveFile = new File(targetFile, fileName);

			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(saveFile));

			byte[] temp = new byte[1024];
			int length = 0;

			while ((length = bis.read(temp)) > 0) {
				bos.write(temp, 0, length);

			}
			bos.flush();

			System.out.println("파일 저장 완료");

			bis.close();
			bos.close();
			socket.close();
			server.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
