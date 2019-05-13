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
		
		//comNum 배열 초기화
		for(int i=0; i<SIZE*SIZE; i++)
			comRand[i] = i+1;
		
		//comNum 배열 섞기
		for(int i=0; i<SIZE*SIZE; i++) {
			int n = (int)(Math.random() * (SIZE*SIZE));	// 0~9중의 한 값을 임의로 얻는다.

			int tmp = comRand[n];
			comRand[n] = comRand[i];
			comRand[i] = tmp;			
		}
		

		// 배열의 모든 요소를 1부터 SIZE*SIZE까지의 숫자로 초기화
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				bingo[i][j] = i*SIZE + j + 1;
			}
		}

		// 배열에 저장된 값을 뒤섞는다.(shuffle)
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				x = (int)(Math.random() * SIZE);
				y = (int)(Math.random() * SIZE);

				// bingo[i][j]와 임의로 선택된 값(bingo[x][y])을 바꾼다.
				int tmp =  bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}

		do {		
			
			printArr(bingo);
			
		
		
			System.out.printf("1~%d의 숫자를 입력하세요.(종료:0)>", SIZE*SIZE);
			String tmp = scanner.nextLine(); // 화면에서 입력받은 내용을 tmp에 저장
			userNum = Integer.parseInt(tmp);     // 입력받은 문자열(tmp)를 숫자로 변환

			// 입력받은 숫자와 같은 숫자가 저장된 요소를 찾아서 0을 저장
			outer:
			for(int i=0;i<SIZE;i++) {
				for(int j=0;j<SIZE;j++) {
					if(bingo[i][j] == userNum) {
						bingo[i][j] = USER;
						break outer; // 2중 반복문을 벗어난다.
					}
				}
			}
			
			//comNum 마킹 (사용자)
			for(int i=0; i<SIZE*SIZE; i++) {
				if(comRand[i] == userNum)
					comRand[i] = USER;
			}
			
			
			//cnum 결정
			for(int i=0; i<SIZE*SIZE; i++) {
				if(!((comRand[i] == USER) || (comRand[i] == COM))) { 
					comNum = comRand[i];
					break;
				}
			}
			
			//comNum 마킹 (컴퓨터)
			for(int i=0; i<SIZE*SIZE; i++) {
				if(comRand[i] == comNum) {
					comRand[i] = COM;
					break;
				}
			}
			
			//컴퓨터 차례
			outer2:
				for(int i=0;i<SIZE;i++) {
					for(int j=0;j<SIZE;j++) {
						if(bingo[i][j] == comNum) {
							bingo[i][j] = COM;
							break outer2; // 2중 반복문을 벗어난다.
						}
					}
				}
						
			
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("==================");
			System.out.println("사용자점수 : " + checkPoint(USER, bingo));
			System.out.println("컴퓨터점수 : " + checkPoint(COM, bingo));			
			System.out.println("==================");			
			System.out.println();			

			
			
		} while(userNum != 0); 
	} // main의 끝
	
	static int checkPoint(int type, int[][] b) {
		int point = 0;
		boolean flag = true;
		
		//빙고 체크
		//가로 체크
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
		
		
		//세로 체크
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
		
		//대각선(\) 체크
		flag = true;
		for(int i=0;i<SIZE;i++) {	
			
			if(bingo[i][i] != type) {
				flag = false;
				break;				
			}			
		}
		
		if(flag)
			point++;
		
		//대각선(/) 체크
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
