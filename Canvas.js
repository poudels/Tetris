/*******************************************************************************************************/
/********************************** Important canvas functions *****************************************/
/*****************************************************************************************************

context.putImageData(imageData, 0, 0); ===== draw a image with imageData array(1D array),
context.getImageData(0, 0, canvas.width, canvas.height); === gives a 1D array of image in canvas









*********************************************************************************************************/

var d = document;
var getId = "getElementById";
var getClass = "getElementsByClassName";

/*******************************************************************************************************/
/********************************** create a canvas **************************************************/
/*****************************************************************************************************/
function init_canvas() {
var div = d.createElement("div");
	div.id = "main_div";
	div.setAttribute("style","height:100%;width:100%;background:rgba(230,230,230,1);");

	var canvas=d.createElement("canvas");
		canvas.id = "canvas";
		canvas.height = "500";
		canvas.width = "1200";


		div.appendChild(canvas);
		d.body.appendChild(div);
}

init_canvas();
var c=document.getElementById("canvas");
var ctx=c.getContext("2d");

/*******************************************************************************************************/
/******************************* Draw a image ************************************************/
/*******************************************************************************************************/

image = new Image();
		image.src = "1943.jpg";
//ctx.drawImage(image, 0, 0);

/*******************************************************************************************************/
/******************************* create linear gradient ************************************************/
/*******************************************************************************************************/
var lGrad = ctx.createLinearGradient(0,0,100,0);
	lGrad.addColorStop(0, "red");
	lGrad.addColorStop(1, "white");

/*******************************************************************************************************/
/******************************** create radial Gradient ***********************************************/
/*******************************************************************************************************/
var rGrad = ctx.createRadialGradient(50, 50, 25, 50, 50, 70);
	rGrad.addColorStop(0, "red");
	rGrad.addColorStop(0.5, "white");
	rGrad.addColorStop(1, "green");

/*******************************************************************************************************/
/************************************************Sierpinski Triangle************************************/
/*******************************************************************************************************/

function Triangle(x, y, size) {
	var canvas = d[getId]("canvas");
	var ctx = canvas.getContext("2d");

	var rGrad = ctx.createRadialGradient(50, 50, 25, 50, 50, 70);
		rGrad.addColorStop(0, "red");
		rGrad.addColorStop(0.5, "white");
		rGrad.addColorStop(1, "green");

	//ctx.clearRect(0, 0, 500, 500);
	var sin = Math.sin(Math.PI/3);
	var cos = Math.cos(Math.PI/3);
	
	ctx.beginPath();
	ctx.moveTo(x, y);
	ctx.lineTo(x + cos * size, y - sin * size);
	ctx.lineTo(x - cos * size, y - sin * size);
	ctx.fill();

	if(size > 5) {
		setTimeout(function(){Triangle(x + cos * size, y, size / 2);},200);
		setTimeout(function(){Triangle(x - cos * size, y, size / 2);},200);
		setTimeout(function(){Triangle(x, y - sin * size, size / 2);},200);
	}
}
//Triangle(250, 500, 250);


/******************************************************************************************************/
/*****************************************Make a image move constantly*********************************/
/******************************************************************************************************/
var i = 0;
function ImageDisplay(x, y, imgWidth, imgHeight) {
	var canvas = d[getId]("canvas");
	var ctx = canvas.getContext("2d");

	var image = new Image();
		image.src = "download.jpg";

	ctx.drawImage(image, 0, 0, imgWidth - x, imgHeight, x , y, imgWidth - x, imgHeight);
	ctx.drawImage(image, 0, 0, imgWidth, imgHeight, x - imgWidth , 10, 1075, imgHeight);
	i++;
}
//setInterval(function(){ImageDisplay(10 + i,10, 1050,300);},50);


/*******************************************************************************************************/
/******************************************* Draw image Slowly *****************************************/
/*******************************************************************************************************/
var timer;
function draw_image(i, j) {
	var imgHeight = 1200;
	var imgWidth = 1920;
	var dx = 20;
	var dy = 20;

	var ratio = 800 / 1920;
	

	image = new Image();
		image.src = "1943.jpg";
	
	ctx.drawImage(image, i, j, dx / ratio, dy / ratio, i * ratio , j * ratio, dx , dy);

	timer = setTimeout(function(){draw_image(i + 40, j)},0);
			
	if(i == imgWidth) {
		timer = window.clearInterval(timer);
		j = j + 40;
		i = -40;
		timer = setTimeout(function(){draw_image(i + 40, j)},0);
	}

	if (j == imgHeight) {
		timer = window.clearInterval(timer);
	}

}
//draw_image(0,0);

/*******************************************************************************************************/
/******************************************* rotate image **********************************************/
/*******************************************************************************************************/
function rotate_image() {
	image = new Image();
		image.src = "1943.jpg";

	ctx.fillStyle="red";
	ctx.fillRect(0,0,300,150);
	
	ctx.clearRect(0, 0, 1200, 800);
	ctx.rotate(3.14 / 10);
	ctx.drawImage(image,0,0, 900,150);
}
//setTimeout(rotate_image,1000);

/*******************************************************************************************************/
/******************************************* Image Filter **********************************************/
/*******************************************************************************************************/

image = new Image();
		image.src = "1943.jpg";

function grayscale(pixels, args) {
	var d = pixels.data;
	for (var i = 0; i < d.length; i += 4) {
		var r = d[i];
		var g = d[i+1];
		var b = d[i+2];
		d[i] = d[i+1] = d[i +2] = (r + g + b) / 3;
	}
	return pixels;
}

// apply a filter to the image data contained in the canvas object
  function filterCanvas(filter) {
    if (canvas.width > 0 && canvas.height > 0) {
      var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
      filter(imageData);
      ctx.putImageData(imageData, 0, 0);
    }
  }

  // filter that shifts all color information to red
  red = function (pixels, args) {
    var d = pixels.data;
    for (var i = 0; i < d.length; i += 4) {
      var r = d[i];
      var g = d[i + 1];
      var b = d[i + 2];
      d[i] = (r+g+b)/3;        // apply average to red channel
      d[i + 1] = d[i + 2] = 0; // zero out green and blue channel
    }
    return pixels;
  };

    // sepia-style filter that gives a warm antique feel to an image
  sepia = function (pixels, args) {
    var d = pixels.data;
    for (var i = 0; i < d.length; i += 4) {
      var r = d[i];
      var g = d[i + 1];
      var b = d[i + 2];
      d[i]     = (r * 0.393)+(g * 0.769)+(b * 0.189); // red
      d[i + 1] = (r * 0.349)+(g * 0.686)+(b * 0.168); // green
      d[i + 2] = (r * 0.272)+(g * 0.534)+(b * 0.131); // blue
    }
    return pixels;
  };

  // filter that brightens an image by adding a fixed value 
  // to each color component
  // a javascript closure is used to parameterize the filter 
  // with the delta value
  brightness = function(delta) {
      return function (pixels, args) {
        var d = pixels.data;
        for (var i = 0; i < d.length; i += 4) {
          d[i] += delta;     // red
          d[i + 1] += delta; // green
          d[i + 2] += delta; // blue   
        }
        return pixels;
      };
  };

ctx.drawImage(image, 0, 0);
setInterval(function(){
	
	//var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
	//console.log(imageData);
	//filterCanvas(brightness(5));

},1000);
