//
// RandomNumberPage.xaml.h
// Declaration of the RandomNumberPage class
//

#pragma once

#include "RandomNumberPage.g.h"

namespace Where_To_Eat	{
	/// <summary> An empty page that can be used on its own or navigated to within a Frame. </summary>
	[Windows::Foundation::Metadata::WebHostHidden]
	public ref class RandomNumberPage sealed		{
		public:
			//Constructors
			RandomNumberPage();

			//Accessor Functions
			int getLowerBound();
			int getUpperBound();
			int getRange();
			int getRandomNumber();

			//Mutator Functions
			void setLowerBound(int newLowerBound);
			void setUpperBound(int newUpperBound);
			void setRange(int newRange);
			void setRandomNumber(int newRandomNumber);

			//Event Functions
			void btnSubmit_Click(Platform::Object^ sender, Windows::UI::Xaml::RoutedEventArgs^ e);

		private:
			//Member variables
			int lowerBound, upperBound, range, randomNumber;

	};//End of class RandomNumberPage	
}//End of namespace Where_To_Eat
