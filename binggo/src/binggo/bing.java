package binggo;

import java.util.*;

class MultiArrEx2 {
	static final int SIZE = 5;
	static final int USER = 1234567;		
	static final int COM = 1234568;
	static int[][] bingo = new int[SIZE][SIZE];
	static int[] comRand = new int[SIZE*SIZE];
	
	public static void main(String[] args) {				
		int x = 0 , y = 0;
		int userNum = 0;
		int comNum = 0;
		int userPoint = 0;
		int comPoint = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		//comNum �迭 �ʱ�ȭ
		for(int i=0; i<SIZE*SIZE; i++)
			comRand[i] = i+1;
		
		//comNum �迭 ����
		for(int i=0; i<SIZE*SIZE; i++) {
			int n = (int)(Math.random() * (SIZE*SIZE));	// 0~9���� �� ���� ���Ƿ� ��´�.

			int tmp = comRand[n];
			comRand[n] = comRand[i];
			comRand[i] = tmp;			
		}
		

		// �迭�� ��� ��Ҹ� 1���� SIZE*SIZE������ ���ڷ� �ʱ�ȭ
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				bingo[i][j] = i*SIZE + j + 1;
			}
		}

		// �迭�� ����� ���� �ڼ��´�.(shuffle)
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				x = (int)(Math.random() * SIZE);
				y = (int)(Math.random() * SIZE);

				// bingo[i][j]�� ���Ƿ� ���õ� ��(bingo[x][y])�� �ٲ۴�.
				int tmp =  bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}

		do {		
			
			printArr(bingo);
			
		
		
			System.out.printf("1~%d�� ���ڸ� �Է��ϼ���.(����:0)>", SIZE*SIZE);
			String tmp = scanner.nextLine(); // ȭ�鿡�� �Է¹��� ������ tmp�� ����
			userNum = Integer.parseInt(tmp);     // �Է¹��� ���ڿ�(tmp)�� ���ڷ� ��ȯ

			// �Է¹��� ���ڿ� ���� ���ڰ� ����� ��Ҹ� ã�Ƽ� 0�� ����
			outer:
			for(int i=0;i<SIZE;i++) {
				for(int j=0;j<SIZE;j++) {
					if(bingo[i][j] == userNum) {
						bingo[i][j] = USER;
						break outer; // 2�� �ݺ����� �����.
					}
				}
			}
			
			//comNum ��ŷ (�����)
			for(int i=0; i<SIZE*SIZE; i++) {
				if(comRand[i] == userNum)
					comRand[i] = USER;
			}
			
			
			//cnum ����
			for(int i=0; i<SIZE*SIZE; i++) {
				if(!((comRand[i] == USER) || (comRand[i] == COM))) { 
					comNum = comRand[i];
					break;
				}
			}
			
			//comNum ��ŷ (��ǻ��)
			for(int i=0; i<SIZE*SIZE; i++) {
				if(comRand[i] == comNum) {
					comRand[i] = COM;
					break;
				}
			}
			
			//��ǻ�� ����
			outer2:
				for(int i=0;i<SIZE;i++) {
					for(int j=0;j<SIZE;j++) {
						if(bingo[i][j] == comNum) {
							bingo[i][j] = COM;
							break outer2; // 2�� �ݺ����� �����.
						}
					}
				}
						
			
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("==================");
			System.out.println("��������� : " + checkPoint(USER, bingo));
			System.out.println("��ǻ������ : " + checkPoint(COM, bingo));			
			System.out.println("==================");			
			System.out.println();			

			
			
		} while(userNum != 0); 
	} // main�� ��
	
	static int checkPoint(int type, int[][] b) {
		int point = 0;
		boolean flag = true;
		
		//���� üũ
		//���� üũ
		for(int i=0;i<SIZE;i++) {
			flag = true;				
			
			for(int j=0;j<SIZE;j++) {
				if(bingo[i][j] != type) {
					flag = false;
					break;				
				}
			}
			
			if(flag)
				point++;				
			
		}			
		
		
		//���� üũ
		for(int i=0;i<SIZE;i++) {
			flag = true;				
			
			for(int j=0;j<SIZE;j++) {
				if(bingo[j][i] != type) {
					flag = false;
					break;				
				}
			}
			
			if(flag)
				point++;
		}
		
		//�밢��(\) üũ
		flag = true;
		for(int i=0;i<SIZE;i++) {	
			
			if(bingo[i][i] != type) {
				flag = false;
				break;				
			}			
		}
		
		if(flag)
			point++;
		
		//�밢��(/) üũ
		flag = true;
		for(int i=0;i<SIZE;i++) {	
			//04 13 22 31 40
			if(bingo[i][SIZE-1-i] != type) {
				flag = false;
				break;				
			}			
		}
		
		if(flag)
			point++;	
		
		return point;
	}
	
	static void printArr(int[][] b) {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(b[i][j] == USER)
					System.out.printf("%2c ", 'U');
				else if(b[i][j] == COM)
					System.out.printf("%2c ", 'C');
				else
					System.out.printf("%2d ", b[i][j]);
				
			}						
			System.out.println();
		}		
		
	}
}
