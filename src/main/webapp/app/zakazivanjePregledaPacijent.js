Vue.component("zakazivanje", {
	props : ['lekarodabran','date','type','klinikaodabrana'],
	data: function () {
	    return {
	    	tipovi: [],
	    	lekari: [],
	    	klinike: [],
	    	sale: [],
	    	tip:"",
	    	lekar:"",
	    	klinika:""
 		
	    }
	},
	template: ` 
<div id = "zakazivanjePregledaPacijent">

<form>
<h2 class="text-center">Zakazivanje pregleda</h2>   
  <div class="form-group">
		<label for = "datetimepicker4">Datum i vreme: </label>
		<input  type='text' class="form-control"  id='datetimepicker4' required />
  </div>
  <div class="form-group">
		<label for = "klinika">Klinika: </label>
		<select id = "klinika"  class= "form-control"  v-model ="klinika" required>
			<option v-for="k in klinike" :value="k.naziv">{{k.naziv}}</option>
		</select>
  </div>
  <div class="form-group">
		<label for = "tip">Tip pregleda: </label>
		<select id = "tip"  class= "form-control" v-model ="tip" required>
			<option v-for="tip in tipovi" :value="tip.naziv">{{tip.naziv}}</option>
		</select>
  </div>
  <div class="form-group">
		<label for = "lekar">Lekar: </label>
		<select id = "lekar"  class= "form-control"  v-model ="lekar" required>
			<option v-for="l in lekari" :value="l.username">{{l.ime}} {{l.prezime}}</option>
		</select>
  </div>
  <div class="form-row">
	<div class="col-sm-5 my-1">
      <button  type="button" class="btn btn-primary" v-on:click="posalji()">Posalji zahtev</button>
    </div>
    <div class="col-sm-6 my-1">
      <button  type="button" class="btn btn-primary" v-on:click="skok()">Nazad na preglede</button>
    </div>
  </div>
</form>



</div>		

`
	, 
	methods : {
		posalji() {
			
			if(!moment( $("#datetimepicker4").val(), 'YYYY-MM-DD HH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        		return;
        	}
			if(!moment( $("#datetimepicker4").val(), 'YYYY-MM-DD HH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        		return;
        	}
			console.log($("#tip").val());
			console.log($("#klinika").val());
			console.log($("#lekar").val());
			console.log($("#datetimepicker4").val());
			axios
		    .get('rest/login/getConcreteUser/Pacijent')
		    .then((response) => {
		    	console.log(response.data);
		    	console.log(response.data.email);
		    	axios
			    .get('rest/Pregled/posalji?datum='+$("#datetimepicker4").val()+'&tip='+$("#tip").val() +'&klinika='+ ($("#klinika").val()).replace(" ",":") +'&lekar='+ $("#lekar").val() +'&pacijent='+ response.data.email)
			    .then((response) => {
			    	alert("Zahtev je poslat!");
			    })
		    	 .catch(function(error){
	 				if(error.response){
	 					alert(error.response.data);
	 				};
		    	 });
		    });
			
        },
        skok(klinika){
        	console.log("skok nazad");
        	this.$emit('nazad');
        },
	},
		
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		console.log("zakazivanje");
		$('#datetimepicker4').datetimepicker();
		
		axios
	    .get('rest/lekari')
	    .then(response => (this.lekari=response.data));
		axios
	    .get('rest/klinika')
	    .then(response => (this.klinike=response.data));
		axios
	    .get('rest/tipPregleda')
	    .then(response => (this.tipovi=response.data));
		
		console.log(this.klinikaodabrana);
		console.log(this.date);
		console.log(this.type);
		console.log(this.lekarodabran.username);
		if (this.lekarodabran == null){
			console.log("Dosao iz pregleda");
		}
		else{
			$("#datetimepicker4").val(this.date);
			this.tip = this.type;
			this.lekar = this.lekarodabran.username;
			this.klinika = this.klinikaodabrana.naziv;
		}
		this.$emit('stigao');
	},	
});