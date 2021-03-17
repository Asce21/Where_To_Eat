using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WhereToEat_Web.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Where To Eat";
            ViewBag.Message = "A tool for the indecicive";

            return View();

        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        public ActionResult ViewRestaurants()
        {
            ViewBag.Title = "ViewRestaurants";
            ViewBag.Message = "Restaurants List";

            return View();
        }
        

        public ActionResult GenerateNumber()
        {
            ViewBag.Title = "Generate Random Number";
            ViewBag.Message = "Enter the lower and upper bounds of the number to generate";

            

            return View();
        }

        /*
         * The following methods are in-page actions
         */
        public ActionResult boundsForm(int lowerBound, int upperBound)
        {
            ViewBag.lower = lowerBound;
            ViewBag.upper = upperBound;
            if (lowerBound < 0 || lowerBound >= upperBound)
                ViewBag.lower = 0;

            if (upperBound < 0 || lowerBound >= upperBound)
                ViewBag.upper = 1;

            Random rnd = new Random();
            int r = rnd.Next(ViewBag.lower, ViewBag.upper);
            ViewBag.RandomNumber = r;

            ViewBag.Title = "Generated Random Number";
            return View("GenerateNumber");
        }
    }
}
 