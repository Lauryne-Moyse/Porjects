/* IMPORTATIONS */
const puppeteer = require('puppeteer');
const fs = require('fs');




/* CREATION OF A WAIT FUNCTION TO MAKE A PAUSE */
function wait(milliseconds) {
    return new Promise(resolve => {
      setTimeout(resolve, milliseconds);
    });
  }




/* IMPORTATION OF THE URL OF THE MOVIE TO SCRAP ON IMDB */
function get_url() {
  try {
    const filePath = 'award_until2019_clean.csv';
    const data = fs.readFileSync(filePath, 'utf8');
    const rows = data.split('\n');
    const urlList = rows.map(row => row.split(',')[25]);
    return urlList;
  } catch (err) {
    console.error('Erreur lors de la lecture du fichier :', err);
    return null;
  }
}




/* RETRIEVING THE HTML CODES OF THE DIFFERENT MOVIE PAGES */
(async () => {
    

    let urlList = get_url();

    // Index of elements to delete (wrong values)
    let indexASupprimer = [86, 134, 146, 159, 266, 312, 393, 522, 568, 600]; 
    for (let i = indexASupprimer.length - 1; i >= 0; i--) {
        let index = indexASupprimer[i];
        urlList.splice(index, 1); 
    }



    const browser = await puppeteer.launch({ headless: false });
    const page = await browser.newPage();


    //We go through the list of urls
    for (let i = 1; i < urlList.length-1; i++) {     

      if (i % 50==0) {await wait(30000);} //We take a break every 50 movies

      console.log(i);
      await page.goto(urlList[i]);
    

      //We retrieve the number of reviews on the page 
      let temp = await page.$('div.header');
      let nb_reviews = 0;
      if (temp) {
              let res = await temp.$('span');
              if (res) { 
                nb_reviews = await page.evaluate(element => element.textContent, res);
                const stopIndex = nb_reviews.lastIndexOf(' ') + 1; 
                nb_reviews = parseInt(nb_reviews.substring(0,stopIndex).replace(/\s/g, ''), 10);  }
      }
      console.log(nb_reviews);
    
      
      let loadMoreButton = await page.$(".ipl-load-more__button");
      //Movies are displayed in increments of 25
      //We click on the load more button the necessary number of times to display them all
      var limit = 0;
      if (nb_reviews%25==0) {limit = nb_reviews/25-1;}
      else {limit = Math.floor(nb_reviews/25);}

      for (let j=0; j<limit; j++) {
          await loadMoreButton.click();
          await wait(5000);
          loadMoreButton = await page.$(".ipl-load-more__button");
      }
    

      // We retrieve the html code of the reviews and add it to a file
      const reviews = await page.$('.article');
      if (reviews) {
          const reviewsHtml = await page.evaluate(element => element.innerHTML, reviews);
          fs.appendFileSync('reviews.html', reviewsHtml); 
      } else {
          console.log("Aucun élément '.article' trouvé.");
    }
  }
  
    //We close the browser
    await browser.close();
  })();
  






    