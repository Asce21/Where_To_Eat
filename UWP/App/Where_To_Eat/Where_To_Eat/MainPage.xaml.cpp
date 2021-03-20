//
// MainPage.xaml.cpp
// Implementation of the MainPage class.
//

#include "pch.h"
#include "RandomNumberPage.xaml.h"
#include "MainPage.xaml.h"

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

// The Blank Page item template is documented at https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x409

//Constructors
MainPage::MainPage()	{
	InitializeComponent();
}//End of the default constructor

//Accessor Functions

//Mutator Functions

//Event Functions
void MainPage::btnGenerateRandomNumber_Click(Platform::Object^ sender, Windows::UI::Xaml::RoutedEventArgs^ e) {
	this->Frame->Navigate(Windows::UI::Xaml::Interop::TypeName(RandomNumberPage::typeid));
}//End of function btnGenerateRandomNumber_Click
