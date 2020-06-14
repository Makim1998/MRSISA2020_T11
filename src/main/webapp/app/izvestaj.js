Vue.component('izvestaj', {
    data: function data() {
        return {
          klinika_id:null,
          pregledi:[],
          operacije:[],
          dataentry: null,
          datalabel: null,
          tacka:[],
          grafik:[["1",1],["2",2]],
          pocetak:null,
          kraj:null,
          ukupno:0,
          picked:"dan",
          ocena:null,
        };
	},
	template: ` 
<div class="oneoption">
<div>
	<h2 class="text-center">Izvjestaj poslovanja klinike</h2>
    <div class="kontejner">	
	  <line-chart :data="grafik"></line-chart>  
	  <h5 style="text-align:center;">Grafik održanih pregleda na određenom vremenskom nivou</h5>     
	</div>
	<div class="izvjestaj-form">
	<div class="l"><p>Od:</p><input type="date" v-model="pocetak"></div>
	<div class="r"><p>Od:</p><input type="date" v-model="kraj"></div>	
	<p style="clear:both">Prikaz grafika odrzanih pregleda na:</p>
	<label class="izvjestaj-radio">Dnevnom nivou
		<input type="radio" name="prikaz" value="dan" v-model="picked">
		<span class="checkmark"></span>
	</label>
	<label class="izvjestaj-radio">Sedmicnom nivou
		<input type="radio" name="prikaz" value="sedmica" v-model="picked">
		<span class="checkmark"></span>
	</label>
	<label class="izvjestaj-radio">Mjesecnom nivou
		<input type="radio" name="prikaz" value="mjesec" v-model="picked">
		<span class="checkmark"></span>
	</label>
	<p>Prosecna ocena klinike: <span id="ocenaklinike">{{ocena}}</span><p>
	<p id="izvjestaj-msg">Prihod klinike za dati vremenski period iznosi <span id="izvjestaj-vr">{{ukupno}}</span></p>
	<button type="button" class="btn btn-primary izvjestaj-btn" v-on:click="prikazimsg()">Prikazi</button">
	</div>
</div>
</div>	 
`
	, 
	methods : {
		prikazimsg(){
			var brojac=0,br=0;
			if(this.picked=="dan"){
				brojac=1;
			}else if(this.picked=="sedmica"){
				brojac=7;
			}else{
				brojac=30;
			}
			this.grafik=[];
			pocinjemo= new Date(this.pocetak);
			zavrsavamo= new Date(this.kraj);
			najranijiPregled=new Date();
			najranijaOperacija=new Date();
			najranije=new Date();
			if(this.pregledi.length!=0){
				najranijiPregled=new Date(this.pregledi[0].datum.substring(0,10));
			}
			if(this.operacije.length!=0){			
				najranijaOperacija=new Date(this.operacije[0].datum.substring(0,10));
			}
			if(najranijiPregled<=najranijaOperacija && najranijiPregled>pocinjemo){
				var a=najranijiPregled.toISOString().substring(0,10).concat(" je najraniji datum od kad mozete pogledati grafik");
				alert(a);
				return;
			}else if(najranijiPregled>=najranijaOperacija && najranijaOperacija>pocinjemo){
				var a=najranijiOperacija.toISOString().substring(0,10).concat(" je najraniji datum od kad mozete pogledati grafik");
				alert(a);
				return;
			}
			this.ukupno=0;
			var kesh=0;
			var trenutno=new Date();
			var brojPregleda=0;
			while(pocinjemo<=zavrsavamo){
				var i;
				var dan=pocinjemo.toISOString();
				dan=dan.substring(0,10);
				//pregledi
				for (i = 0; i < this.pregledi.length; i++) {
					trenutno= new Date(this.pregledi[i].datum.substring(0,10));
					if(trenutno.getTime()==pocinjemo.getTime()){
						kesh+=this.pregledi[i].cena.cena;
						brojPregleda=brojPregleda+1;
					}
				}
				//operacije
				for (i = 0; i < this.operacije.length; i++) {
					trenutno= new Date(this.operacije[i].datum.substring(0,10));
					if(trenutno.getTime()==pocinjemo.getTime()){
						kesh+=this.operacije[i].cena.cena;
						brojPregleda=brojPregleda+1;
					}
				}
				br+=1;
				if(br==brojac){
					this.grafik.push([dan,brojPregleda]);
					this.ukupno+=kesh;
					br=0;
					kesh=0;
					brojPregleda=0;
				}
				pocinjemo.setDate(pocinjemo.getDate() + 1);
			}
			pocinjemo.setDate(pocinjemo.getDate() - 1);
			if(trenutno.getTime()!=pocinjemo.getTime()){
				dan=pocinjemo.toISOString();
				this.grafik.push([dan,brojPregleda]);
				this.ukupno+=kesh;
			}
			document.getElementById("izvjestaj-msg").style.display="block";
		},
		uredi(id,naziv) {
			this.id=id;
			this.izmena=naziv;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        }
	},
	mounted(){
		$('#datetimepicker4').datetimepicker();
		axios
		.get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
			axios
		    .get('rest/login/getKlinika')
		    .then(response =>{
		    	this.klinika_id=response.data.id;
		    	this.ocena=response.data.prosecnaOcena;
				axios
			    .get('rest/Pregled/zavrseni/'+response.data.id,response.data.id)
			    .then(response => (this.pregledi=response.data));
				axios
			    .get('rest/Operacija/zavrseni/'+response.data.id,response.data.id)
			    .then(response => (this.operacije=response.data));
				
		    });
	    })
	    .catch(response => {
			this.$router.push("/");
		});

	},
});