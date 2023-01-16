import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2006 {
	static int userWin, comWin;
	static int winCondtion = 0;
	static int game;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("번호를 입력하세요 : ");
		int N = Integer.parseInt(br.readLine());

		switch (N) {
		case 1:
			game = 5;
			winCondtion = 3;
			break;
		case 2:
			game = 3;
			winCondtion = 2;
			break;
		case 3:
			game = 1;
			winCondtion = 1;
			break;
		}

		int gameCnt = 0;
		while (gameCnt < game) {
			System.out.print("가위바위보 중 하나 입력 : ");

			String user = br.readLine();
			int com = (int) (Math.random() * 3) + 1;

			switch (user) {
			case "가위":
				if (com == 2) {
					comWin++;
				} else if (com == 3) {
					userWin++;
				}
				gameCnt++;
				break;
			case "주먹":
				if (com == 1) {
					userWin++;
				} else if (com == 3) {
					comWin++;
				}
				gameCnt++;
				break;
			case "보":
				if (com == 1) {
					comWin++;
				} else if (com == 2) {
					userWin++;
				}
				gameCnt++;
				break;
			}

			if (userWin == winCondtion) {
				System.out.println("###사람 승!");
				flag = true;
				break;
			} else if (comWin == winCondtion) {
				System.out.println("###컴퓨터 승!!!");
				flag = true;
				break;
			}

		}

		if (!flag) {
			System.out.println("승부가 나지 않았습니다.");
		}

	}
}
