Vue.component('godisnjiPrihvatanje', {
	data: function () {
	    return {
	    	razlog:null,
	    	prihvacenOdbijen:null,
	    	tipovi:[],
	    	id:null,
	    	izmena:"",
	    	salaPK:{
	    		brojSale:null,
	    		klinika:null
	    	}
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Godisnji odmori/odsustva</h2>
	  <p>Prihvatanje i odbijanje.</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Datum pocetka</th>
		   <th>Datum kraja</th>
		   <th>Prihvatanje</th>
		   <th>Odbijanje</th>
		</tr>
		<tr v-for="tp in tipovi" class="filterDiv " >
			<td class="myclass">{{tp.korisnik.kc_id}}</td>
			<td class="myclass">{{tp.korisnik.ime}}</td>
			<td class="myclass">{{tp.korisnik.prezime}}</td>		
			<td class="myclass">{{tp.datumPocetka.substring(0,10)}}</td>
			<td class="myclass">{{tp.datumKraja.substring(0,10)}}</td>
			<td><input class="btn btn-success btn-lg" value='Prihvati' type='button'  v-on:click="uredi(true,tp.id,tp.datumPocetka,tp.datumKraja,tp.korisnik.id)"/></td>
			<td><input class="btn btn-danger btn-lg" value='Odbij' type='button' v-on:click="uredi(false,tp.id,tp.datumPocetka,tp.datumKraja,tp.korisnik.id)"/></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div class="form-popup" id="myForm">
    <input type="textarea" id = "prezime" class="form-control" v-model="razlog" placeholder="Navedite razlog prihvatanja/odbijanja..">
    <button type="button" class="btn maal leftbutton" v-on:click="potvrdi()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		uredi(n,a,b,c,d) {
			this.prihvacenOdbijen=n;
			this.id=a;
			this.pocetak=b;
			this.kraj=c;
			this.med_id=d;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
		otkazi() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		potvrdi() {
			axios
			.post('rest/godisnji/prihvati',{
			    	"id":this.id,
			        "datumPocetka": this.pocetak,
			        "datumKraja": this.kraj,
			        "razlog":this.razlog,
			        "prihvacenOdbijen":this.prihvacenOdbijen,
			        "medOsoblje_id":this.med_id,
			        
			    })
			    .then((response) => {
			    	axios
				    .get('rest/godisnji/svi/'+response.data.id,response.data.id)
				    .then(response => (this.tipovi=response.data));
					document.getElementById("myForm").style.display = "none";
					document.getElementById("modaldark").style.display = "none";
					document.getElementById("modaldark").style.opacity="0";   	
			    });
	    },
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
		    console.log(response.data);	
		})
		.catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/login/getKlinika')
	    .then(response =>{
	    	axios
		    .get('rest/godisnji/svi/'+response.data.id,response.data.id)
		    .then(response => (this.tipovi=response.data));
	    });
	}
});