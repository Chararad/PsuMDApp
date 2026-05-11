#include <iostream>
#include <conio.h>
#include <ctime>
#include <cstdlib>

using namespace std;
const int fixed_lives = 5;
void BossHint(int boss_phase, int guess, int randomNum) {

    int diff = abs(randomNum - guess);

    switch(boss_phase) {

        case 1: {
            int chance = rand() % 3;     
            bool lie = (chance == 0);

            if (!lie) {
                if (guess < randomNum) cout << "Higher";
                else cout << "Lower";
            } else {
                if (guess < randomNum) cout << "Lower";
                else cout << "Higher";
            }
            break;
        }
        case 2: {
            if (guess < randomNum) cout << "Higher";
            else cout << "Lower";
            break;
        }
        case 3: {
            if (guess < randomNum) cout << "Higher";
            else cout << "Lower";
            break;
        }
        case 4: {
            if (guess < randomNum) cout << "Higher";
            else cout << "Lower";
            break;
        }
        case 5: {
            cout << "You are " << diff << " away.";
            break;
        }
        default: {
            if (guess < randomNum) cout << "Higher";
            else cout << "Lower";
        }
    }
}
void Game(int num){
	
	int lives = fixed_lives;
	int range;
	int boss_phase = 0;
	int boss_lives = 5;
	int boss1;
	do{
		cout << "Minimum range: 20" << endl;
		cout << "Number Range: ";
		cin >> range;
		if(range < 20){
			cout << "Invalid Range!" << endl;
			getch();
			system("cls");
		}
	}
	while(range < 20);
	
	
	if(num == 4){
		cout << "Clue Master: Trust not all you see. Drawing near might scare the number. Some move a little, some leap far, but all\n depends on where they are. You\'ll see its ends only if it\'s weakened. Iterative tricks won\'t work so much - observe\n carefully, guess wisely." << endl;
	}
	cout << "Enter to Start" << endl;
	getch();
	if(num == 4){
		system("cls");
	}
	time_t start = time(0);
	
	srand(time(0));
	int randomNum = (rand() % range) + 1;
	int guess = -1;
	int guesses = 0;
	bool guessed = false;
	
	if(num == 2){
		cout << "You got 5 lives" << endl;
	}
	if(num == 4){
		cout << "!!!! BOSS STAGE !!!!" << endl;
		cout << "Phase 1: M@n!pu|@To^" << endl;
		boss_phase ++;
	}
	while(guessed != true){
		guessed = false;
		cin.exceptions(ios::failbit);
		while(true){
			while(true){
				cout << "Guess the Number 1-" << range  << ": ";
				try {
            	    cin >> guess;
                	break;
            	}
            	catch(ios_base::failure &e) {
                	cout << "Insert a Valid Number" << endl; 
                	cout << "Guess the Number 1-" << range  << ": ";
                	cin.clear();
                	char temp[100];
                	cin.getline(temp, 100);
            	}
        	}
        	if(guess > 0 && guess <= range){
        		break;
			}
			else {
				cout << "Invalid Input" << endl;
			}
        	
		}
		guesses += 1;
		if(guess == randomNum){
			cout << "You Guessed the Number" << endl;
			if(num != 4){
				guessed = true;
			}
			if(num == 4){
				boss_lives --;
				boss_phase ++;
			}
			if(num == 4 && boss_phase != 6){
				
				for(int x = 0; x < boss_lives; x++){
					cout << "[*]";
				}
				for(int x = 0; x < boss_phase - 1; x++){
					cout << "[X]";
				}
				cout << endl;
				if(boss_phase == 2){
					randomNum = (rand() % range) + 1;
					cout << "Phase 2: (Force Field)" << endl;
				}
				else if(boss_phase == 3){
					randomNum = (rand() % range) + 1;
					cout << "Phase 3: !|}}Chaos{{|!" << endl;
				}
				else if(boss_phase == 4){
					randomNum = (rand() % range) + 1;
					cout << "Phase 4: [{}]!!!! &3RS3RK !!!![{}]" << endl;
				}
				else if(boss_phase == 5){
					randomNum = (rand() % range) + 1;
					cout << "Phase 5: weakened mode" << endl;
				}
			}
			else if(num == 4 && boss_lives == 0){
				guessed = true;
				cout << "[X][X][X][X][X]" << endl;
				cout << "You defeated the boss!" << endl;
				getch();
				system("cls");
			}
		}
		else if(guess < randomNum) {
			boss1 = (rand() % 3) + 1;
			BossHint(boss_phase, guess, randomNum);
			cout << endl;
			
			if(num == 2 && lives != 0){
				lives -= 1;
				cout << "You got " << lives << " lives left." << endl;
			}
			if(num == 4 && (guess > randomNum-5 && guess < randomNum+5) && boss_phase == 3){
				cout << "!!!! BOSS CHANGED THE NUMBER !!!!" << endl;
				randomNum *= 2; 
				
				if(randomNum > range){
					randomNum = randomNum%(range +1);
				}
				if(randomNum == 0){
					randomNum++;
				}
			}
			else if (num == 4 && (guess > randomNum-10 && guess < randomNum+10) && boss_phase == 2){
				cout << "Force Field Moves the Number!" << endl;
				boss1 = (rand() % 2) + 1;
				if(boss1 == 1){
					randomNum++;
				}
				else if(boss1 == 2){
					randomNum --;
				}
				if(randomNum > range){
					randomNum -= 2;
				}
				else if(randomNum <= 0){
					randomNum += 2;
				}
			}
			else if(num == 4 && (guess > randomNum-2 && guess < randomNum+2) && boss_phase == 4){
				randomNum = (rand() % range) +1;
				cout << "BOSS WENT BERSERK! NUMBER TELEPORTED!" << endl;
			}
		}
		else if (guess > randomNum) {
			boss1 = (rand() % 3) + 1;
			BossHint(boss_phase, guess, randomNum);
			cout << endl;
			
			if(num == 2 && lives != 0){
				lives -= 1;
				cout << "You got " << lives << " lives left." << endl;
			}
			if(num == 4 && (guess > randomNum-5 && guess < randomNum+5) && boss_phase == 3){
				cout << "!!!! BOSS CHANGED THE NUMBER !!!!" << endl;
				randomNum *= 2; 
				
				if(randomNum > range){
					randomNum = randomNum%(range +1);
				}
				if(randomNum == 0){
					randomNum++;
				}
			}
			else if (num == 4 && (guess > randomNum-10 && guess < randomNum+10) && boss_phase == 2){
				cout << "Force Field Moves the Number!" << endl;
				boss1 = (rand() % 2) - 1;
				if(boss1 == 1){
					randomNum++;
				}
				else if(boss1 == 2){
					randomNum --;
				}
				if(randomNum > range){
					randomNum -= 2;
				}
				else if(randomNum <= 0){
					randomNum += 2;
				}
			}
			else if(num == 4 && (guess > randomNum-2 && guess < randomNum+2) && boss_phase == 4){
				randomNum = (rand() % range) +1;
				cout << "BOSS WENT BERSERK! NUMBER TELEPORTED!" << endl;
			}
		}
		
		if(num == 2 && lives == 0  && guessed != true){
			system("cls");
			cout << "You Lost" << endl;
			cout << "The Number is " << randomNum << endl;
			getch();
			break;
		}
	}
	time_t end = time(0);
	int duration = difftime(end, start);
	int seconds = duration % 60;
	int minute = duration/60;
	
	if(num == 3){
		cout << "Scores "<< endl;
		cout << "Guesses: " << guesses << endl;
		if(minute != 0){
			cout << "Duration: " << minute << "m " << seconds << "s " << endl;
		}
		else{
			cout << "Duration: " << seconds << "s" << endl;
		}
		getch();
		system("cls");
	}
	
}

int main(){
	int choice = -1;
	
	while(choice != 5){
		cout << "Welcome to Guess the Number!!!!" << endl;
		cout << "Choose the difficulty!!" << endl;
		cout << "1 - Normal Guess the Number" << endl;
		cout << "2 - 5 Lives Game" << endl;
		cout << "3 - Timed Version" << endl;
		cout << "4 - Boss" << endl;
		cout << "5 - Exit Game" << endl;
		cout << "Enter Choice: "; cin >> choice;
		
		system("cls");
		switch(choice){
			case 1: Game(choice);
				break;
			case 2: Game(choice);
				break;
			case 3: Game(choice);
				break;
			case 4: Game(choice);
				break;
			case 5: cout << "Exiting Game!" << endl;
				break;
			default:
				break;
		}
		
	}

	
	
	
	return 0;
}
