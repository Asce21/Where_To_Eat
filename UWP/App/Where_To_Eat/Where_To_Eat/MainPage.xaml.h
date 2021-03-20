//
// MainPage.xaml.h
// Declaration of the MainPage class.
//

#pragma once

#include "MainPage.g.h"

namespace Where_To_Eat	{
	/// <summary>
	/// An empty page that can be used on its own or navigated to within a Frame.
	/// </summary>
	public ref class MainPage sealed		{
		public:
			//Constructors
			MainPage();

			//Accessor Functions

			//Mutator Functions

			//Event Functions
			void btnGenerateRandomNumber_Click(Platform::Object^ sender, Windows::UI::Xaml::RoutedEventArgs^ e);

		private:
			//Member variables

	};//End of class MainPage
}//End of namespace Where_To_Eat
