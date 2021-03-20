//
// RandomNumberPage.xaml.cpp
// Implementation of the RandomNumberPage class
//

#include "pch.h"
#include "RandomNumberPage.xaml.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string>

using namespace Where_To_Eat;

using namespace Platform;
using namespace Windows::Foundation;
using namespace Windows::Foundation::Collections;
using namespace Windows::UI::Xaml;
using namespace Windows::UI::Xaml::Controls;
using namespace Windows::UI::Xaml::Controls::Primitives;
using namespace Windows::UI::Xaml::Data;
using namespace Windows::UI::Xaml::Input;
using namespace Windows::UI::Xaml::Media;
using namespace Windows::UI::Xaml::Navigation;
using namespace Windows::UI::Popups;

// The Blank Page item template is documented at https://go.microsoft.com/fwlink/?LinkId=234238

//Constructors
RandomNumberPage::RandomNumberPage()	{
	InitializeComponent();
}//End of the default constructor


//Accessor Functions
int RandomNumberPage::getLowerBound() {	return lowerBound; }
int RandomNumberPage::getUpperBound() { return upperBound; }
int RandomNumberPage::getRange() { return range; }
int RandomNumberPage::getRandomNumber() { return randomNumber; }

//Mutator Functions
void  RandomNumberPage::setLowerBound(int newLowerBound) { lowerBound = newLowerBound; }
void  RandomNumberPage::setUpperBound(int newUpperBound) { upperBound = newUpperBound; }
void  RandomNumberPage::setRange(int newRange) { range = newRange; }
void RandomNumberPage::setRandomNumber(int newRandomNumber) { randomNumber = newRandomNumber; }

//Event Functions
void RandomNumberPage::btnSubmit_Click(Platform::Object^ sender, Windows::UI::Xaml::RoutedEventArgs^ e) {
	//Assign the variables lowerBound / upperBound with the values in the text box
	String ^sLower = tbLowerBound->Text;
	String ^sUpper = tbUpperBound->Text;
	const wchar_t* wLower = sLower->Data();
	//const wchar_t* wUpper = sUpper->Data();
	lowerBound = std::wcstol(wLower, nullptr, 4);
	std::wstring wsUpper(sUpper->Begin());
	std::string stUpper(wsUpper.begin(), wsUpper.end());
	upperBound = std::stoi(stUpper);

	//Calculate the range
	if (upperBound >= lowerBound) {
		range = upperBound - lowerBound + 1;

		//Generate A Random Number
		srand((unsigned int)time(NULL));
		randomNumber = rand() % range + lowerBound;

		//Print the Random Number
		MessageDialog ^generatedNumber = ref new MessageDialog("Your random number is\n" + randomNumber, "Generated Number");
		generatedNumber->ShowAsync();
	} else    	{
		//Print the error messag
		MessageDialog ^errorMessage = ref new MessageDialog("Your lower bound must be less than or equal to your upper bound", "Incorrect Range");
		errorMessage->ShowAsync();
	}//End of the if / else block to check for a correct range	
}//End of the function btnSubmit_Click
