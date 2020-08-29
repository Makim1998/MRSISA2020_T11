Vue.component('zahtevi', {
	data: function(){
		return {
			zahtevi: [],
			id: null,
			razlog: "",
			karton:{
				pol: "",
				datum: "",
				visina: "",
				tezina: "",
				krvna: "",
				alergije: "",
				istorija: ""
			},
			ime: "",
			prezime: ""
		}
	},
	template: `
<div class="oneoption_AKC">
<div>
<h2 class="text-center">Zahtevi za registracije</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Id</th>
		   <th>Email</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Prihvatanje</th>
		   <th>Odbijanje</th>
		</tr>
		<tr v-for="z in zahtevi" class="filterDiv " >
			<td class="myclass">{{z.id}}</td>
			<td class="myclass">{{z.email}}</td>
			<td class="myclass">{{z.ime}}</td>
			<td class="myclass">{{z.prezime}}</td>
			<td><input class="btn btn-primary btn-lg" value='Prihvati' type='button'  v-on:click="prihvati(z.id,z.ime,z.prezime)"/></td>
			<td><input class="btn btn-primary btn-lg" value='Odbij' type='button' v-on:click="odbij(z.id)"/></td>
		</tr>	
   </table>
   <div id="modaldark">
		<div class="form-popup" id="myForm">
			<h6>Navedite razlog za odbijanje zahteva:</h6>
			<textarea style="width:250px;height:150px;" class="psw" v-model="razlog"></textarea>
			</br></br>
			<button type="button" class="btn maal leftbutton" v-on:click="potvrda()">Potvrdi</button>
			<button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
		</div>
	</div>
	<div id="AKC-nov-karton">
		<form id="AKC-karton-form">
		<h2 class="text-center">Zdravstveni karton</h2>       
    	<div class="lform-group">
			<label for="pol">Pol: </label>
   			<select class="form-control" v-model="karton.pol">
   				<option>MUSKO</option>
   				<option>ZENSKO</option>
   			</select>
		</div>
		<div class="lform-group">
			<label for="datetimepicker4">Datum rodjenja: </label>
    		<input type="text" id = "datetimepicker4" class="form-control"  v-model="karton.datum">
		</div>
		<div class="lform-group">
			<label for="visina">Visina: </label>
    		<input type="text"  id = "visina" class="form-control" v-model="karton.visina">
		</div>
		<div class="lform-group">
			<label for="tezina">Tezina: </label>
    		<input type="text"  id = "tezina" class="form-control" v-model="karton.tezina">
		</div>
		<div class="lform-group">
			<label for="krvna">Krvna grupa: </label>
    		<input type="text"  id = "krvna" class="form-control" v-model="karton.krvna">
		</div>
		<div class="lform-group">
			<label for="alergije">Alergije: </label>
    		<input type="text"  id = "alergije" class="form-control" v-model="karton.alergije" placeholder="(Moze da ostane prazno)">
		</div>
		<div class="lform-group">
			<label for="istorija">Istorija bolesti: </label>
    		<textarea style="height:80px;" id="istorija" class="form-control" v-model="karton.istorija" placeholder="(Moze da ostane prazno)"></textarea>
		</div>
		<div id="btns">
	       <input class="btn btn-secondary" value='Otkazi' type='button' v-on:click="otkazi()"/>
	       <input class="btn btn-primary" value='Zavrsite' type='button' v-on:click="registruj();"/>
	    </div>
		</form>
	</div>
</div>
</div>
	`
	,
	methods: {
		prihvati(id,ime,prezime){
			console.log("Stiglo je do frontend-a za prihvatanje");
			/*
			axios
			.put('rest/pacijent/prihvati/'+id)
			.then(response => {
				axios
				.get('rest/pacijent/zahtevi')
				.then(response => (this.zahtevi=response.data));
			});*/
			this.id=id;
			this.ime=ime;
			this.prezime=prezime;
			document.getElementById("AKC-nov-karton").style.display = "block";
			document.getElementById("AKC-karton-form").style.display = "block";
			
		},
		provera(){
			if (this.karton.visina.trim() == "")
				return true;
			else if (this.karton.tezina.trim() == "")
				return true;
			else if (this.karton.krvna.trim() == "")
				return true;
			else if (this.karton.datum.trim() == "")
				return true;
			else if (this.karton.pol == "")
				return true;
			else if (this.karton.datum == "")
				return true;
			else
				return false;
		},
		proveraDatum(){
			if(!moment( $("#datetimepicker4").val(), 'DD.MM.YYYY.', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (DD.MM.YYYY.)");
        		return true;
        	}
			if(!moment( $("#datetimepicker4").val(), 'DD.MM.YYYY.', true).isBefore(moment())){
        		alert("Ne mozete da unesete datum i vreme u buducnosti!");
        		return true;
        	}
		},
		registruj(){
			if (this.provera())
				alert("Niste uneli nesto od polja za karton!");
			else if (this.proveraDatum())
				console.log();
			else{
				axios
				.put('rest/pacijent/prihvati/'+this.id)
				.then(response => {
					axios
					.put('rest/pacijent/createKarton/'+this.id, {"id":null, "ime":this.ime, "prezime":this.prezime, "krvnaGrupa":this.karton.krvna,
						"visina":this.karton.visina, "tezina":this.karton.tezina, "alergije":this.karton.alergije,
						"istorijaBolesti":this.karton.istorija, "datumRodjenja":this.karton.datum, "polStr":this.karton.pol})
						.then(response => {
							axios
							.get('rest/pacijent/zahtevi')
							.then(response => (this.zahtevi=response.data));
						});
				});
				this.otkazi();
			}
		},
		odbij(id){
			console.log("Stiglo je do frontend-a za odbijanje");
			this.id = id;
        	document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		potvrda(){
        	if (this.razlog.trim() == "")
        		alert("Niste uneli razlog za odbijanje zahteva!");
        	else{
        		axios
    			.delete('rest/pacijent/odbij/'+this.id+"/"+this.razlog)
    			.then(response => {
    				axios
    				.get('rest/pacijent/zahtevi')
    				.then(response => (this.zahtevi=response.data));
    			});
        		this.otkazi();
        	}
        },
        otkazi(){
        	document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("AKC-nov-karton").style.display = "none";
			document.getElementById("AKC-karton-form").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminKC')
	    .then((response) => {
		    console.log(response.data);	
		})
		.catch(response => {
			this.$router.push("/");
		});
		axios
		.get('rest/pacijent/zahtevi')
		.then(response => (this.zahtevi=response.data));
		$("#datetimepicker4").val(this.date);
	}
})