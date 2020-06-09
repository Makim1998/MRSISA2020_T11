Vue.component("klinikePacijent", {
	data: function () {
	    return {
	    	tipovi: [],
	    	poruka:{
	    		datum:"",
	    		tip:""
	    	},
	    	input: {	    		 
                naziv: "",
                opis: "",
                centar: "",
                adresa: ""
            		},
	    	klinike:[],
	    	zaOcenu:"",
	    	ocena:"",
	    	filter: {
	    		naziv: "",
	    		adresa: "",
                prosek: "",
                tip: "",
                datum: ""
	    	}
            		
	    }
	},
	template: ` 
<div id = "klinikePacijent">
<h2 class="text-center">Klinike</h2>

<div id = "filterKlinike">
<form>
  <div class="form-row">
    <div class="col-sm-2 my-1">
      <label for = "prosecnaOcena" class="col-form-label">Prosecna ocena: </label>
		<select id = "prosecnaOcena" v-model = "filter.prosek" class= "form-control">
			<option value = "">Sve</option>
			<option value = "1">1-2</option>
			<option value = "2">2-3</option>
			<option value = "3">3-4</option>
			<option value = "4">4-5</option>
		</select>
    </div>
    <div class="col-sm-2 my-1">
      <label for = "naziv" class="col-sm-2 col-form-label">Naziv: </label>
		<input id = "naziv" type = "text" v-model = "filter.naziv" class= "form-control">
    </div>
    <div class="col-sm-3 my-1">
      <label for = "adresa" class="col-sm-2 col-form-label">Adresa: </label>
		<input id = "adresa" type = "text" v-model = "filter.adresa" class= "form-control">
    </div>
    <div class="col-sm-1 my-1">
		<button id = "b" type="button" class="btn btn-primary" data-toggle="modal" data-target="#pretraziKlinikuModal"">Pretrazi</button>
    </div>
    <div class="col-sm-2 my-1">
      <button id = "b" type="button" class="btn btn-primary" v-on:click="ponistiFilter()">Prikazi sve</button>
    </div>
  </div>
</form>
</div><br>

<table class="table">
  <thead>
    <tr>
      <th scope="col">Naziv</th>
      <th scope="col">Adresa</th>
      <th scope="col">Opis</th>
      <th scope="col">Prosecna ocena</th>
    </tr>
  </thead>
  <tbody>
		<tr v-for="k in filtriraneKlinike">
			<td >{{k.naziv}}</td>
			<td >{{k.adresa}}</td>
			<td >{{k.opis}}</td>
			<td >{{k.prosecnaOcena}}</td>
			<td>
			<button type="button" v-on:click = "zaOcenu = k.naziv" class="btn btn-primary" data-toggle="modal" data-target="#oceniKlinikuModal">
				Oceni kliniku
			</button>
			</td>
			<td>
			<button type="button" v-on:click = "skok(k)" class="btn btn-primary">
				Odaberi kliniku
			</button>
			</td>
		</tr>
  </tbody>
</table>

<div>
<h5 class="text-center" id = "rezultatiPretrage"></h5>
<h3 class="text-center" v-if="rezultati" ></h3>
</div>

<!-- Modal -->
<div class="modal fade" id="pretraziKlinikuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Pretraga klinika</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "datetimepicker4">Datum i vreme: </label>
				<input  type='text' class="form-control"  id='datetimepicker4' required />
		    </div>
		    <div class="form-group">
		      	<label for = "tip">Tip pregleda: </label>
				<select id = "tip" v-model = "filter.tip" class= "form-control" required>
					<option v-for="tip in tipovi" :value="tip.naziv">{{tip.naziv}}</option>
				</select>
		    </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" v-on:click="pretraga()">Pretrazi klinike</button>
      </div>
    </div>
  </div>
</div>



<!-- Modal -->
<div class="modal fade" id="oceniKlinikuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ocena klinike: {{zaOcenu}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<label for = "selekt">Ocena: </label>
        <select id= "selekt" class= "form-control" v-model ="ocena">
        	<option value = "1">1</option>
        	<option value = "2">2</option>
        	<option value = "3">3</option>
        	<option value = "4">4</option>
        	<option value = "5" selected>5</option>
        </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" v-on:click="oceni()">Posalji ocenu</button>
      </div>
    </div>
  </div>
</div>


</div>		

`
	, 
	methods : {
		oceni() {
        	console.log("ocena");
        	console.log(this.zaOcenu);
        	console.log(this.ocena);

        	console.log("ocena");
        	axios
    	    .post('rest/klinika/ocena?ocena='+this.ocena +'&klinika='+this.zaOcenu )
    	    .then(response => {
    	    	$('#oceniKlinikuModal').modal('hide');
            	$('.modal-backdrop').remove();
    	    	console.log("uspeh");
    	    	this.init()});
    	    
        	
        },
        pretraga() {
        	console.log("pretraga");
        	console.log(this.filter.datum);
        	console.log($("#datetimepicker4").val());
        	
        	if(!moment( $("#datetimepicker4").val(), 'YYYY-MM-DD HH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        		return;
        	}
        	if(!moment( $("#datetimepicker4").val(), 'YYYY-MM-DD HH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        		return;
        	}
        	if(this.filter.tip == undefined || this.filter.tip === ""){
        		alert("Unesite ispravno tip pregleda!");
        		return;
        	}
        	this.poruka.datum = $("#datetimepicker4").val();
        	this.poruka.tip = this.filter.tip;
        	axios
    	    .get('rest/klinika/pretraga?datum='+$("#datetimepicker4").val() +'&tip='+this.filter.tip +'&naziv='+this.filter.naziv +'&adresa='+this.filter.adresa +'&prosek='+this.filter.prosek)
    	    .then(response => {
    	    	$('#pretraziKlinikuModal').modal('hide');
            	$('.modal-backdrop').remove();
    	    	console.log("uspeh");
    	    	this.klinike = response.data});
    	    
        	
        },
        ponistiFilter(){
        	console.log("klinike");
        	this.filter.adresa = "";
        	this.filter.prosek = "";
        	this.filter.naziv = "";
        	axios
    	    .get('rest/klinika')
    	    .then(response => (this.klinike=response.data));
        },
        skok(klinika){
        	console.log("skok");
        	this.$emit('poruka',this.poruka);
        	this.$emit('skok',klinika);
        },
		init(){
        	console.log("klinike");
    		axios
    	    .get('rest/klinika')
    	    .then(response => (this.klinike=response.data));
		}
	},
	computed: {
		filtriraneKlinike: function(){
			console.log("prosek");
			console.log(this.filter.prosek);
			console.log(this.filter.naziv);
			console.log(this.filter.adresa);
			
			return this.klinike.filter((klinika)=>{
				console.log("hhha")
				console.log(klinika.adresa.toLowerCase());
				console.log(this.filter.adresa.toLowerCase());
				console.log(klinika.adresa.toLowerCase().match(this.filter.adresa.toLowerCase()) == null)
				console.log("hhha");

				if(klinika.adresa.toLowerCase().match(this.filter.adresa.toLowerCase()) == null){
					return false;
				}
				if(klinika.naziv.toLowerCase().match(this.filter.naziv.toLowerCase()) == null){
					return false;
				}
				if(this.filter.prosek == ""){
					return true;
				}
				if(this.filter.prosek == undefined){
					return true;
				}
				return parseFloat(klinika.prosecnaOcena) >= parseFloat(this.filter.prosek) &&
				parseFloat(klinika.prosecnaOcena) <= parseFloat(this.filter.prosek) + 1;
			});
		},
		rezultati: function(){
			console.log(this.filtriraneKlinike.length)
			console.log(this.filtriraneKlinike.length == 0)
			if(this.filtriraneKlinike.length == 0){
				console.log("nema nadjenih");
				$("#rezultatiPretrage").html("Nema rezultata pretrage");
				return true;
			}
			this.bulean = false;
			$("#rezultatiPretrage").html("");
			return false;

		}
	
	},
	mounted(){
		console.log("klinike");
		console.log(this.$parent.component);
		$('#datetimepicker4').datetimepicker();
		axios
	    .get('rest/klinika')
	    .then(response => (this.klinike=response.data));
		axios
	    .get('rest/tipPregleda')
	    .then(response => (this.tipovi=response.data));
	},	
});