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
	    	},
	    	klinika_id:null,
	    	email:null
	    }
	},
	template: ` 
<div class="oneoption">
<div>
<h2 class="text-center">Godisnji odmor/odsustva</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Email</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Datum pocetka</th>
		   <th>Datum kraja</th>
		   <th>Prihvatanje</th>
		   <th>Odbijanje</th>
		</tr>
		<tr v-for="tp in tipovi" class="filterDiv " >
			<td class="myclass">{{tp.korisnik.email}}</td>
			<td class="myclass">{{tp.korisnik.ime}}</td>
			<td class="myclass">{{tp.korisnik.prezime}}</td>		
			<td class="myclass">{{tp.datumPocetka.substring(0,10)}}</td>
			<td class="myclass">{{tp.datumKraja.substring(0,10)}}</td>
			<td><input class="btn btn-primary btn-lg" value='Prihvati' type='button'  data-toggle="modal" data-target="#prihvati" v-on:click="uredi(tp.korisnik.email,true,tp.id,tp.datumPocetka,tp.datumKraja,tp.korisnik.id)"/></td>
			<td><input class="btn btn-primary btn-lg" value='Odbij' type='button' data-toggle="modal" data-target="#prihvati" v-on:click="uredi(tp.korisnik.email,false,tp.id,tp.datumPocetka,tp.datumKraja,tp.korisnik.id)"/></td>
		</tr>	
   </table>
   <!-- Modal -->
<div class="modal fade" id="prihvati" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Email: {{this.email}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "prezime">Navedite razlog prihvatanja/odbijanja </label>
				<textarea  style="height:300px;max-height:300px;" id = "prezime" class="form-control" v-model="razlog"></textarea>
		    </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" data-dissmiss="modal" v-on:click="potvrdi()">Potvrdi</button>
      </div>
    </div>
  </div>
</div>
   <!---div id="modaldark">
   <div class="form-popup" id="myForm">
    <textarea style="height:300px;" id = "prezime" class="form-control" v-model="razlog" placeholder="Navedite razlog prihvatanja/odbijanja.."></textarea>
    <button type="button" class="btn maal leftbutton" v-on:click="potvrdi()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div---->
</div>
</div>		  
`
	, 
	methods : {
		uredi(em,n,a,b,c,d) {
			this.email=em;
			this.prihvacenOdbijen=n;
			this.id=a;
			this.pocetak=b;
			this.kraj=c;
			this.med_id=d;
			/*document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
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
				    .get('rest/godisnji/svi/'+this.klinika_id,this.klinika_id)
				    .then(response => (this.tipovi=response.data));
			    	$('#prihvati').modal('hide');
		        	$('.modal-backdrop').remove();
					/*document.getElementById("myForm").style.display = "none";
					document.getElementById("modaldark").style.display = "none";
					document.getElementById("modaldark").style.opacity="0";*/   	
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
	    	this.klinika_id=response.data.id;
	    	axios
		    .get('rest/godisnji/svi/'+response.data.id,response.data.id)
		    .then(response => (this.tipovi=response.data));
	    	
	    });
	}
});