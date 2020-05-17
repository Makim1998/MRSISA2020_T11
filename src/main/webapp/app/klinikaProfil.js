Vue.component("klinikaProfil", {
	data:function(){
		return{
			id:null,
            naziv: "",
            adresa: "",
            opis: "",
            kc_id:null,
            prosecnaOcena:"",
            moscowMap:null
		}
	},
	template: ` 
<div class="oneoption">
<div id = "login" class="sidenavlogin">
    <form>
		<h2 class="text-center">Profil klinike</h2>       
		<div class="form-group">
			<label for="username">ID Klinike: </label>
   			<input type="text" id = "username" class="form-control" v-model="id"  disabled>
		</div>
		<div class="form-group">
			<label for="ime">Naziv: </label>
   			<input type="text" id = "ime" class="form-control" v-model="naziv" placeholder="Naziv">
    	</div>
    	<div class="form-group">
    		<label for="adresa">Adresa: </label>
   			<input type="textbox" id = "adresa" class="form-control" v-model="adresa" placeholder="Adresa">
		</div>
		<div class="form-group">
			<div id="map"></div>
		</div>
		<div class="form-group">
    		<label for="opis">Opis: </label>
   			<textarea id = "opis" class="form-control" v-model="opis" placeholder="Opis"></textarea>
		</div>
		<div class="form-group">
			<label for="username">Prosecna ocena: </label>
   			<input type="text" id = "username" class="form-control" v-model="prosecnaOcena"  disabled>
		</div>
		<div class="form-group">
    		<button type="button" id="submit" class="btn btn-primary btn-block" v-on:click="izmeni()">Izmeni profil</button>
		</div>
    </form>
</div>	
</div>	
  		  
`
	, 
	methods : {
		izmeni() {
			axios
		    .put('rest/klinika/izmeni',{
		    	"id":this.id,
		        "naziv": this.naziv,
		        "adresa": this.adresa,
		        "opis":this.opis,
		    })
		    .then((response) => {
		    	alert("Podaci su izmenjeni");
		    	this.id=response.data.id;
		    	this.naziv=response.data.naziv;
		    	this.adresa=response.data.adresa;
		    	this.opis=response.data.opis;
		    	var moscow_map;
		    	var lokacija=this.adresa.split(' ').join('%20');
		    	var str1="https://geocoder.ls.hereapi.com/6.2/geocode.json?searchtext=";
		    	var str2=lokacija;
		    	var str3="&gen=9&apiKey=aXyfkX0kZp1Zd6gsXK6qCOrSfozCSa7Euf7uHOHmmPc";
		    	var veb=str1.concat(str2,str3);
		    	
		    	ymaps.ready(function(){
					axios
				    .get(veb)
				    .then((response) => {
				    	var latitude=response.data.Response.View[0].Result[0].Location.DisplayPosition.Latitude;
				    	var longitude=response.data.Response.View[0].Result[0].Location.DisplayPosition.Longitude;
				    	document.getElementById('map').innerHTML="";
				    	this.moscowMap = new ymaps.Map('map', {
				    		center:[latitude,longitude],
						    zoom: 13
				    	});
				    	placemark = new ymaps.Placemark(this.moscowMap.getCenter());
				    	this.moscowMap.geoObjects.add(placemark);
				    });
		        }); 
		    });
        },
	},
	mounted(){
    	var moscowMap; 
		axios
	    .get('rest/login/getKlinika')
	    .then((response) => {
	    	this.id=response.data.id;
	    	this.naziv=response.data.naziv;
	    	this.adresa=response.data.adresa;
	    	this.opis=response.data.opis;
	    	this.prosecnaOcena=response.data.prosecnaOcena;
	    	var moscow_map;
	    	/*var myGeocoder = ymaps.geocode("Petrozavodsk");
	    	myGeocoder.then(
	    	    function (res) {
	    	        alert('Object coordinates:' + res.geoObjects.get(0).geometry.getCoordinates());
	    	    },
	    	    function (err) {
	    	        alert('Error');
	    	    }
	    	);*/
	    	var lokacija=this.adresa.split(' ').join('%20');
	    	var str1="https://geocoder.ls.hereapi.com/6.2/geocode.json?searchtext=";
	    	var str2=lokacija;
	    	var str3="&gen=9&apiKey=aXyfkX0kZp1Zd6gsXK6qCOrSfozCSa7Euf7uHOHmmPc";
	    	var veb=str1.concat(str2,str3);
	    	//alert(veb);
	    	ymaps.ready(function(){
				axios
			    .get(veb)
			    .then((response) => {
			    	//alert(response.data.Response);
			    	var latitude=response.data.Response.View[0].Result[0].Location.DisplayPosition.Latitude;
			    	var longitude=response.data.Response.View[0].Result[0].Location.DisplayPosition.Longitude;
			    	this.moscowMap = new ymaps.Map('map', {
			    		center:[latitude,longitude],
					    zoom: 13
			    	});
			    	placemark = new ymaps.Placemark(this.moscowMap.getCenter());
			    	this.moscowMap.geoObjects.add(placemark);
			    });
	        });  

		})
		.catch(response => {
			this.$router.push("/");
		});
	}
});