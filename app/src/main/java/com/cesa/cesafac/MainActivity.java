package com.cesa.cesafac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesa.cesafac.R;

import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    //Variabile pentru disable button 24 H.
    private boolean firstTimeUse =false;

    private String firstTimeKey = "FIRST_TIME";
    private String sharedPreferesekey = "MY_PREF";
    private String buttonKey = "BUTTON_CLICK";
    private SharedPreferences mprefs;
    private long saveDATA = 0;
    //incheiere variabile pentru disable button 24 H

    //variabile pentru salvare txt dupa restart
    public static final String SHARED_PREF = "shared";

    public static final String TEXT = "";
    public static String text = "";
    //Incheiere variabile salvare text

    private TextView txt;
    Button btn;
    private Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for disable button
         mprefs = getSharedPreferences(sharedPreferesekey, Context.MODE_PRIVATE);
        saveDATA = mprefs.getLong(buttonKey,0);
        firstTimeUse= mprefs.getBoolean(firstTimeKey,true);
        checkPrefs();
        //end for disable button




        txt = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        about = findViewById(R.id.button2);


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_popup();

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int rando = (int) ((Math.random())) ;
                // Generare index aleatoriu
                lista_random();

                saveClickedTime();

                mesajRevenire();

                //salvare dupa restart aplicatie
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT,txt.getText().toString());
                editor.apply();


            }
        });


        update();

    }

    private void update() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
        txt.setText(text);
    }


    private void saveClickedTime(){

        SharedPreferences.Editor editor = mprefs.edit();
        Calendar cal = Calendar.getInstance();
        long millis = cal.getTimeInMillis();
        editor.putLong(buttonKey,millis);
        editor.putBoolean(firstTimeKey,false);
        editor.commit();

        //button enable false
        findViewById(R.id.button).setEnabled(false);

    }


   private  void checkPrefs(){

        if(firstTimeUse==false){
            if(saveDATA>0){
                Calendar currentCal = Calendar.getInstance();
               currentCal.set(Calendar.MINUTE,1);
                currentCal.set(Calendar.HOUR,1);
                currentCal.set(Calendar.SECOND,1);
                currentCal.set(Calendar.MILLISECOND,1);

                Calendar savedcal = Calendar.getInstance();
                savedcal.setTimeInMillis(saveDATA);
                savedcal.set(Calendar.MINUTE,1);
               savedcal.set(Calendar.HOUR,1);
                savedcal.set(Calendar.SECOND,1);
                savedcal.set(Calendar.MILLISECOND,1);

                if(currentCal.getTime().after(savedcal.getTime())){
                    //button enable tru
                    findViewById(R.id.button).setEnabled(true);

                }if(currentCal.getTime().equals(savedcal.getTime())){
                    //button enable false
                    findViewById(R.id.button).setEnabled(false);

                }else{
                    //button enable true
                    findViewById(R.id.button).setEnabled(true);
                }
            }
       }

    }



    public void mesajRevenire(){
       ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"");
    }


    private void show_popup(){

        Dialog popup = new Dialog(this);
        popup.setContentView(R.layout.popup_lay);
        popup.show();

        ImageView close_popup = popup.findViewById(R.id.imageButton4);

        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup.dismiss();
            }
        });


    }



    private void lista_random(){

        //int rando = (int) ((Math.random())*x) ;
        String[][] lista = {
                {"Analizează avantajele și dezavantajele fiecărei opțiuni și evaluează care îți oferă cele mai mari beneficii sau îți potrivește cel mai bine."},
                {"Fii recunoscător pentru resursele pe care le ai și ai încredere în puterea ta de a le utiliza în mod eficient. Nu subestima niciun resursă, deoarece fiecare poate juca un rol important în atingerea obiectivelor tale."},
                {"Analizează avantajele și dezavantajele fiecărei opțiuni și evaluează care îți oferă cele mai mari beneficii sau îți potrivește cel mai bine."},
                {"Analizează cu atenție opțiunile și consecințele fiecărei alegeri."},
                {"Apelează la metode de relaxare și introspecție pentru a te conecta cu intuiția ta."},
                {"Ascultă-ți inima și alege ceea ce îți aduce pacea și fericirea interioară."},
                {"Ascultă-ți inima și alege ceea ce simți că este corect pentru tine."},
                {"Ascultă-ți inima și alege ceea ce te face cu adevărat fericit și împlinit."},
                {"Ascultă-ți intuiția și alege ceea ce simți că este corect pentru tine."},
                {"Ascultă-ți intuiția și alege ceea ce simți că este în armonie cu scopul și valorile tale personale."},
                {"Ascultă-ți intuiția și învață să acorzi încredere vocea ta interioară."},
                {"Ascultă-ți intuiția și urmează-ți instinctul."},
                {"Caută informații suplimentare și cercetează opțiunile disponibile."},
                {"Cere sfatul și perspectiva unor persoane în care ai încredere și care au experiență în domeniul respectiv."},
                {"Consultă modele de succes și inspirație pentru a obține perspective suplimentare."},
                {"Consultă oameni de încredere și cere-ți părerea lor."},
                {"Consultă resurse și informații relevante pentru a-ți sprijini luarea deciziei."},
                {"Explorează soluții creative și găsește o cale de mijloc dacă există mai multe opțiuni aparent incompatibile."},
                {"Fă o listă cu avantajele și dezavantajele fiecărei opțiuni pentru a vizualiza mai clar situația."},
                {"Fii atent la semnele și indiciile pe care viața ți le oferă în luarea deciziilor."},
                {"Fii autentic cu tine însuți și alege calea care reflectă cine ești cu adevărat."},
                {"Fii conștient de echilibrul dintre inimă și minte și găsește calea care le îmbină în mod armonios."},
                {"Fii conștient de emoțiile tale și alege ceea ce te împuternicește și te eliberează de frică."},
                {"Fii conștient de limitele și resursele tale și alege în consecință."},
                {"Fii conștient de limitele și resursele tale și ia în considerare aceste aspecte în luarea deciziilor."},
                {"Fii conștient de propria ta intuiție și conectează-te cu ea înainte de a lua decizii importante."},
                {"Fii conștient de resursele și capacitățile tale și alege ceea ce ți se potrivește cel mai bine."},
                {"Fii conștient de resursele și capacitățile tale și ia în considerare aceste aspecte în luarea deciziilor."},
                {"Fii conștient de valorile și principiile tale și alege calea care este în armonie cu acestea."},
                {"Fii deschis la feedback și observații din partea celor din jur, dar păstrează-ți autonomia în luarea deciziei."},
                {"Fii deschis la feedback-ul și sugestiile celor din jur, dar nu uita că decizia finală este a ta."},
                {"Fii deschis la noi oportunități și la variante neașteptate și inovatoare."},
                {"Fii deschis la noi perspective și opțiuni pe care poate nu le-ai luat în considerare inițial."},
                {"Fii deschis la opțiuni noi și inovatoare și fii dispus să ieși din tiparele tradiționale."},
                {"Fii deschis la perspectiva și feedback-ul celor din jur, dar amintește-ți că tu ești cel care cunoaște cel mai bine situația ta."},
                {"Fii deschis la perspectiva și feedback-ul celorlalți, dar păstrează-ți autonomia în luarea deciziilor finale."},
                {"Fii deschis la perspectiva și feedback-ul celorlalți, dar păstrează-ți autonomia în luarea deciziilor."},
                {"Fii deschis la schimbare și la adaptarea planurilor tale în funcție de circumstanțe noi."},
                {"Fii deschis la schimbare și la posibilitatea de a te adapta la situații noi și neprevăzute."},
                {"Fii pregătit să iei decizii în funcție de circumstanțele actuale și să te adaptezi la schimbările neașteptate."},
                {"Fii pregătit să înveți din greșeli și să te ajustezi pe parcurs, dacă este necesar."},
                {"Fii pregătit să înveți din greșeli și să te ajustezi pe parcurs."},
                {"Fii pregătit să îți asumi riscuri și să ieși din zona ta de confort."},
                {"Fii pregătit să te asumi responsabilitatea pentru deciziile tale și să înveți din consecințe."},
                {"Fii pregătit să-ți asumi responsabilitatea pentru deciziile tale și să te înfrunți cu consecințele lor."},
                {"Fii pregătit să-ți asumi riscuri și să ieși din zona ta de confort pentru a-ți atinge obiectivele."},
                {"Fii pregătit să-ți asumi riscuri și să înveți din greșeli, în căutarea alegerii potrivite."},
                {"Fii sincer cu tine însuți și alege ceea ce te face cu adevărat fericit."},
                {"Fii sincer cu tine însuți și recunoaște-ți adevăratele dorințe și nevoi în luarea deciziei."},
                {"Gândește-te la beneficiile pe termen lung și la cum decizia ta poate contribui la creșterea și împlinirea ta personală."},
                {"Gândește-te la beneficiile pe termen lung și la cum decizia ta poate contribui la viitorul tău."},
                {"Gândește-te la care este opțiunea care îți oferă oportunitatea de a-ți valorifica talentele și abilitățile."},
                {"Gândește-te la care este opțiunea care te ajută să te dezvolți și să-ți atingi potențialul maxim."},
                {"Gândește-te la care este opțiunea care te ajută să te simți autentic și în armonie cu tine însuți."},
                {"Gândește-te la care este opțiunea care te ajută să te simți în echilibru și armonie cu tine însuți și cu lumea din jur."},
                {"Gândește-te la care este opțiunea care te ajută să-ți atingi scopurile și să te simți în împlinire personală."},
                {"Gândește-te la care este opțiunea care te ajută să-ți exploatezi și să-ți valorifici potențialul."},
                {"Gândește-te la care este opțiunea care te încurajează să-ți depășești fricile și să ieși din zona ta de confort."},
                {"Gândește-te la care este opțiunea care te încurajează să-ți îmbrățișezi vulnerabilitățile și să te dezvolți în mod autentic."},
                {"Gândește-te la care este opțiunea care te încurajează să-ți urmezi pasiunile și să te simți în armonie cu tine însuți."},
                {"Gândește-te la care este opțiunea care te încurajează să-ți urmezi pasiunile și să-ți atingi visurile."},
                {"Gândește-te la care este opțiunea care te îndeamnă să te aliniezi cu scopul și misiunea ta de viață."},
                {"Gândește-te la care este varianta care te ajută să crești și să-ți depășești limitele."},
                {"Gândește-te la ce vrei să realizezi în viitor și alege calea care te apropie cel mai mult de acele obiective."},
                {"Gândește-te la consecințele pe termen lung ale deciziilor tale și alege calea care îți susține valorile și obiectivele personale."},
                {"Gândește-te la consecințele pe termen lung și la cum decizia ta poate contribui la evoluția și dezvoltarea ta personală."},
                {"Gândește-te la consecințele pe termen lung și la impactul pe care decizia ta îl va avea asupra ta și a celorlalți."},
                {"Gândește-te la cum te simți în legătură cu fiecare opțiune și alege ceea ce îți inspiră bucurie și împlinire."},
                {"Gândește-te la cum vei privi decizia ta peste câțiva ani și alege în consecință."},
                {"Gândește-te la obiectivele tale pe termen lung și alege calea care te apropie cel mai mult de acele obiective."},
                {"Gândește-te la resursele disponibile și la cum vei gestiona cerințele fiecărei opțiuni."},
                {"Gândește-te la valorile și principiile tale și alege calea care este în armonie cu acestea."},
                {"Gândește-te la valorile tale fundamentale și alege calea care le susține."},
                {"Ia în considerare impactul deciziei tale asupra celorlalți și asupra mediului."},
                {"Ia în considerare sfaturile și opiniile persoanelor care te cunosc bine și au interesul tău la inimă."},
                {"Identifică avantajele și dezavantajele fiecărei alegeri și ponderază-le."},
                {"Încadrează întrebarea într-un context mai larg și ia în considerare consecințele pe termen lung."},
                {"Încarcă-te cu energie pozitivă și încredere înainte de a lua decizia."},
                {"Încearcă să-ți imaginezi cum te-ai simți cu fiecare opțiune și alege calea care îți inspiră cel mai mult entuziasm și împlinire."},
                {"Încurajează-te și îți dăruiește încredere în capacitatea ta de a face alegeri înțelepte."},
                {"Înțelege că nicio decizie nu este definitivă și că poți ajusta și adapta în timp."},
                {"Înțelege că uneori nu există un răspuns corect sau greșit și alegerea ta depinde de preferințe și priorități personale."},
                {"Întreabă-te care este cea mai bună opțiune pentru tine în acest moment, având în vedere circumstanțele actuale."},
                {"Întreabă-te care este direcția care îți oferă oportunitatea de a crește și de a te dezvolta."},
                {"Întreabă-te care este opțiunea care te ajută să te dezvolți și să evoluezi ca persoană."},
                {"Întreabă-te care este opțiunea care te încurajează să-ți depășești limitele și să crești ca persoană."},
                {"Întreabă-te care este opțiunea care te încurajează să-ți dezvolți abilitățile și talentele și să te exprimi în mod autentic."},
                {"Întreabă-te care este opțiunea care te motivează și te inspiră să fii cea mai bună versiune a ta."},
                {"Învață din experiențele trecute și aplică înțelepciunea acumulată."},
                {"Învață din experiențele trecute și cum ai gestionat situații similare înainte și folosește această înțelepciune pentru a-ți ghida decizia."},
                {"Învață să acorzi încredere procesului de luare a deciziilor și să te bazezi pe rațiune și intuiție în egală măsură."},
                {"Învață să delegi și să ceri ajutor atunci când este necesar."},
                {"Învață să faci compromisuri, dar asigură-te că nu te abați de la valorile tale fundamentale."},
                {"Învață să fii răbdător și să acorzi timp procesului de luare a deciziilor, în special în situații complexe."},
                {"Învață să gândești în perspectivă și să iei în considerare consecințele pe termen lung ale deciziei tale."},
                {"Învață să gestionezi emoțiile și să iei decizii rationale, ținând cont de factorii obiectivi."},
                {"Învață să gestionezi frica și îndoielile și să alegi calea care te împinge înainte, chiar și în fața provocărilor."},
                {"Învață să gestionezi incertitudinea și să iei decizii în condiții de informații incomplete."},
                {"Învață să îți asculți nevoile și să alegi în funcție de ele."},
                {"Învață să tolerezi incertitudinea și să te adaptezi la schimbare."},
                {"Învață să-ți asculți inima și să acorzi încredere sentimentelor și intuiției tale."},
                {"Învață să-ți asculți intuiția și să acorzi încredere procesului tău de gândire."},
                {"Învață să-ți asculți intuiția și să ai încredere în deciziile tale, chiar dacă par îndrăznețe sau neconvenționale."},
                {"Învață să-ți asculți intuiția și să te bazezi pe înțelepciunea interioară în luarea deciziilor."},
                {"Învață să-ți asculți vocea interioară și să fii autentic în luarea deciziilor."},
                {"Învață să-ți asculți vocea interioară și să urmezi ceea ce simți că este corect pentru tine."},
                {"Învață să-ți asumi riscuri calculat și să fii deschis la oportunități noi."},
                {"Prioritizează valorile și obiectivele tale personale."},
                {"Pune în balanță beneficiile pe termen scurt și pe termen lung ale fiecărei alegeri."},
                {"Pune în balanță beneficiile pe termen scurt și pe termen lung ale fiecărei alegeri."},
                {"Reflectează asupra lecțiilor învățate din experiențele anterioare și aplică aceste învățăminte în luarea deciziilor."},
                {"Ai puterea de a alege să fii fericit. Nu lăsa circumstanțele să-ți determine starea de spirit."},
                {"Ai puterea de a alege să fii un model de integritate și valori. Fii o inspirație pentru ceilalți prin propriul exemplu."},
                {"Ai puterea de a alege să te concentrezi asupra lucrurilor pozitive și de a-i inspira pe ceilalți să facă același lucru. Împărtășește lumina ta cu lumea."},
                {"Ai puterea de a fi sursa propriei tale fericiri. Nu permite circumstanțelor să-ți controleze starea de spirit."},
                {"Ai puterea de a ierta și de a te elibera de resentimente. Iertarea aduce pace și creștere personală."},
                {"Ai puterea de a inspira și de a schimba viețile altor oameni. Fii un exemplu de bunătate și compasiune."},
                {"Ai puterea de a învăța din fiecare experiență și de a crește ca persoană. Nu te teme să faci greșeli, ci vezi-le ca pe oportunități de a învăța și de a te dezvolta."},
                {"Ai puterea de a învăța din greșeli și de a crește mai puternic decât înainte. Eșecurile sunt lecții prețioase pe care le poți folosi pentru a-ți atinge succesul."},
                {"Ai puterea de a învăța din greșeli și de a crește mai puternic decât înainte. Eșecurile sunt lecții prețioase."},
                {"Ai puterea de a învăța din trecut și de a construi un viitor mai bun. Fii conștient de lecțiile pe care viața ți le oferă și folosește-le pentru a-ți crea propria ta poveste de succes."},
                {"Ai puterea de a învăța și de a crește în fiecare zi. Fii deschis la noi cunoștințe și experiențe."},
                {"Ai puterea de a spune 'da' la oportunități și de a-ți deschide ușile către noi experiențe și creștere personală."},
                {"Ai puterea de a te concentra pe lucrurile pe care le poți controla și de a lăsa în urmă cele pe care nu le poți controla."},
                {"Ai puterea de a te ridica după fiecare eșec și de a încerca din nou. Perseverența este cheia succesului."},
                {"Ai puterea de a te schimba și de a evolua. Nu te limita la ceea ce ai fost, ci fii deschis către ceea ce poți deveni."},
                {"Ai puterea de a transforma fiecare zi într-o oportunitate de a te dezvolta și de a-ți îmbunătăți viața."},
                {"Ai puterea de a-ți alege cuvintele cu grijă și de a aduce lumina și inspirația în viețile celorlalți. Folosește-ți puterea cuvintelor pentru a încuraja, motiva și sprijini pe ceilalți în călătoria lor."},
                {"Ai puterea de a-ți alege propria taie. Nu lăsa influențele externe să-ți dicteze direcția vieții tale."},
                {"Ai puterea de a-ți asuma responsabilitatea pentru propria ta fericire. Nu aștepta ca alții să te facă fericit, ci alege să fii fericit de unul singur."},
                {"Ai puterea de a-ți controla gândurile și emoțiile. Alege să te concentrezi pe lucruri pozitive și să-ți menții un mindset constructiv."},
                {"Ai puterea de a-ți crea propria fericire. Nu o căuta în exterior, ci descoper-o în interiorul tău."},
                {"Ai puterea de a-ți crea propria realitate prin gândurile, acțiunile și atitudinea ta. Alege să te concentrezi pe lucrurile pe care le dorești și vei vedea cum se materializează în viața ta."},
                {"Ai puterea de a-ți crea propria ta realitate. Visează în mare și acționează cu încredere."},
                {"Ai puterea de a-ți crea propriul succes prin munca și angajamentul tău. Nu aștepta ca succesul să vină de la sine, ci acționează și fă-ți propriile talea."},
                {"Ai puterea de a-ți crea propriul succes și de a-ți îndeplini visurile. Nu aștepta ca alții să-ți ofere oportunități, ci fii proactiv și crează-ți propria taie."},
                {"Ai puterea de a-ți exprima recunoștința și aprecierea pentru tot ceea ce ai în viață. Fii conștient de bogăția din jurul tău."},
                {"Ai puterea de a-ți găsi echilibrul și de a-ți prioritiza sănătatea și bunăstarea ta. Fii conștient de nevoile tale și acționează în consecință."},
                {"Ai puterea de a-ți găsi scopul în viață și de a urma pasiunea ta. Nu-ți teme călătoria, ci încrederea în tine și în abilitățile tale."},
                {"Ai puterea de a-ți găsi vocea și de a te exprima autentic în lume. Nu-ți fie teamă să fii diferit și să fii în totalitate cine ești."},
                {"Ai puterea de a-ți găsi vocea și de a-ți exprima opinia în mod autentic. Nu-ți ascunde adevărul pentru a te conforma, ci exprimă-te liber."},
                {"Ai puterea de a-ți ierta trecutul și de a merge mai departe. Iertarea aduce eliberare și te ajută să-ți construiești un viitor mai luminos."},
                {"Ai puterea de a-ți înfrunta fricile și de a ieși din zona ta de confort. În acel spațiu se află creșterea și descoperirea de sine."},
                {"Ai puterea de a-ți înfrunta fricile și de a merge în direcția lor. Învinge-ți temerile și vei descoperi că există libertate și creștere în afara zonei tale de confort."},
                {"Ai puterea de a-ți înfrunta fricile și de a te elibera de limitările tale. Ieși din zona ta de confort și vei descoperi o lume plină de posibilități neexplorate."},
                {"Ai puterea de a-ți înfrunta temerile și de a-ți urma visurile. Curajul nu înseamnă absența fricii, ci acțiunea în ciuda ei."},
                {"Ai puterea de a-ți iubi și aprecia pe tine însuți. Fii bun cu tine și încrederea în propria ta valoare. Ești mai puternic decât crezi și poți realiza lucruri minunate în viața ta."},
                {"Ai puterea de a-ți manifesta talentele și abilitățile. Lasă-ți lumina să strălucească și inspiră lumea cu ceea ce ai de oferit."},
                {"Ai puterea de a-ți manifesta visele în realitate prin acțiuni constante și hotărâte. Nu permite niciunei piedici să te oprească în drumul tău către succes."},
                {"Ai puterea de a-ți transforma gândurile negative în gânduri pozitive. Alege să te concentrezi pe aspectele pozitive ale vieții și vei vedea cum energia ta se schimbă și atragi lucruri bune în viața ta."},
                {"Ai puterea de a-ți transforma visele în planuri și planurile în acțiuni. Acționează cu hotărâre și vei vedea cum devin realitate."},
                {"Ai puterea de a-ți transforma visele în realitate prin acțiune și determinare. Fiecare pas mic pe care îl faci te apropie mai mult de succesul dorit."},
                {"Ai puterea de a-ți transforma visurile în realitate. Nu renunța niciodată la ele."},
                {"Ajută pe cei aflați în nevoie și fii generos cu timpul și resursele tale."},
                {"Amintește-ți că fiecare zi este o oportunitate de a învăța și de a crește. Ai puterea de a te dezvolta în fiecare moment."},
                {"Atunci când crezi în tine, ai puterea de a cuceri orice provocare."},
                {"Atunci când te confrunți cu dificultăți, amintește-ți că ai puterea de a-ți învinge fricile și de a obține succesul pe care îl meriți."},
                {"Când simți că nu mai poți, amintește-ți că ai traversat deja atât de multe obstacole și ai demonstrat că ești puternic."},
                {"Când simți că te afunzi, amintește-ți că ai puterea de a te ridica și de a-ți continua drumul."},
                {"Când te confrunți cu dificultăți, amintește-ți că ai puterea de a le depăși și de a-ți atinge obiectivele."},
                {"Când te simți înfrânt, amintește-ți că ai puterea de a te ridica și de a încerca din nou."},
                {"Crede în tine însuți și în abilitățile tale. Ai puterea de a-ți crea propriul destin."},
                {"Crede în tine și în abilitățile tale. Ai puterea de a-ți transforma visele în realitate."},
                {"Crede în tine și în capacitățile tale. Ai puterea de a-ți atinge visurile și de a depăși obstacolele."},
                {"Demonstrează integritate în tot ceea ce faci."},
                {"Fiecare eșec este o oportunitate de a-ți dezvolta rezistența și de a-ți demonstra puterea interioară."},
                {"Fiecare zi este o oportunitate pentru a-ți arăta puterea interioară și a face lucruri remarcabile."},
                {"Fii altruist și adu bucurie în viața celorlalți."},
                {"Fii altruist și ajută-i pe ceilalți să-și descopere propria putere. În acest fel, vei crește și tu însuți."},
                {"Fii autentic și ai încredere în puterea ta de a fi tu însuți. Nu-ți compara drumul cu alții, ci mergi pe calea ta."},
                {"Fii autentic și autentic în tot ceea ce faci, să reflecte valorile și convingerile tale."},
                {"Fii autentic și onest cu tine însuți înainte de toate."},
                {"Fii conștient de impactul acțiunilor tale asupra altora și asupra mediului."},
                {"Fii conștient de puterea cuvintelor tale. Folosește-le pentru a încuraja, inspira și aduce lumină în viața celor din jur."},
                {"Fii conștient de puterea ta de a alege gândurile și perspectivele tale. Alegerea ta determină felul în care trăiești viața."},
                {"Fii conștient de puterea ta de a-ți crea propria fericire. Fericirea nu vine din exterior, ci din interiorul tău. Alege să fii fericit și să găsești bucurie în lucrurile simple ale vieții."},
                {"Fii conștient de puterea ta de a-ți influența propria viață. Alege să fii arhitectul destinului tău și să-ți construiești o viață plină de fericire și satisfacție."},
                {"Fii curajos și ai încredere în puterea ta de a te exprima autentic. Lumea are nevoie de tine așa cum ești."},
                {"Fii curajos și ai încredere în puterea ta de a te ridica deasupra criticilor și de a-ți continua drumul spre succes."},
                {"Fii curajos și ai încredere în puterea ta de a-ți asuma riscuri. Cele mai mari recompense vin atunci când îți depășești temerile și ieși din zona ta de confort."},
                {"Fii curajos și îndrăznește să urmezi drumul tău. Numai așa vei cunoaște adevărata ta putere."},
                {"Fii curajos și înfruntă provocările cu încredere și determinare."},
                {"Fii curajos și înfruntă temerile tale."},
                {"Fii curajos și urmărește-ți pasiunile. Ai puterea de a-ți construi o viață plină de satisfacție și împlinire."},
                {"Fii dedicat și disciplinat în urmărirea pasiunilor tale și în atingerea obiectivelor tale."},
                {"Fii deschis la colaborare și învață să lucrezi în echipă cu ceilalți."},
                {"Fii deschis la idei și opiniile altora și manifestă respect."},
                {"Fii deschis la învățare și căută oportunități de creștere personală."},
                {"Fii deschis la oportunități noi și ai încredere în puterea ta de a-ți exploata potențialul la maxim. Nu ți limita posibilitățile prin teamă sau îndoială."},
                {"Fii deschis la schimbare și ai încredere în puterea ta de a evolua. În fiecare zi poți deveni o versiune mai bună a ta."},
                {"Fii deschis la schimbare și ai încredere în puterea ta de a te adapta și de a crește. Flexibilitatea și adaptabilitatea sunt cheile unei vieți pline de succes și împlinire."},
                {"Fii deschis la schimbare și învață din experiențele și feedback-ul celorlalți."},
                {"Fii deschis la schimbare și învață din noi experiențe."},
                {"Fii determinat și ai încredere în puterea ta de a depăși obstacolele și de a-ți atinge obiectivele. Nu renunța niciodată la ceea ce îți dorești cu adevărat."},
                {"Fii determinat și ai încredere în puterea ta de a-ți depăși limitele și de a-ți atinge potențialul maxim. Nu te limita la ceea ce știi deja, ci explorează noi domenii și dezvoltă-ți abilitățile."},
                {"Fii determinat și ai încredere în puterea ta de a-ți învinge propriile limite. Nu-ți subestima potențialul."},
                {"Fii disciplinat și ai încredere în puterea ta de a-ți menține angajamentele și de a-ți atinge obiectivele."},
                {"Fii disciplinat și lucrează constant pentru a-ți atinge obiectivele."},
                {"Fii empatic cu tine însuți și îngrijește-ți sănătatea și bunăstarea."},
                {"Fii empatic față de cei aflați în suferință și oferă sprijin."},
                {"Fii empatic și ascultă cu atenție pe ceilalți."},
                {"Fii empatic și încurajează empatia în relațiile tale."},
                {"Fii hotărât și ai încredere în puterea ta de a-ți atinge obiectivele. Dedică-te cu pasiune și perseverență."},
                {"Fii încrezător și ai încredere în puterea ta de a depăși orice provocare. Credința în sine este cheia pentru a-ți atinge potențialul maxim."},
                {"Fii îndrăzneț și ai încredere în puterea ta de a te ridica în fața adversităților. Nu lăsa nimic să-ți stingă entuziasmul și pasiunea."},
                {"Fii îndrăzneț și ai încredere în puterea ta de a-ți asuma riscuri. Cele mai mari recompense vin din acțiunile îndrăznețe."},
                {"Fii înțelegător și acordă șanse oamenilor să se schimbe."},
                {"Fii întotdeauna sincer și onest în tot ceea ce faci."},
                {"Fii modest și nu lăsa succesul să-ți urce la cap."},
                {"Fii motivat de pasiunea ta și ai încredere în puterea ta de a-ți atinge visurile. Nimic nu este imposibil atunci când îți urmezi pasiunea."},
                {"Fii motivat de pasiunea ta și ai încredere în puterea ta de a-ți atinge visurile. Nimic nu este imposibil atunci când îți urmezi pasiunea."},
                {"Fii motivat de progresul tău și ai încredere în puterea ta de a-ți atinge obiectivele. Fiecare pas mic contează și te apropie mai mult de succes."},
                {"Fii motivat de propria ta viziune și ai încredere în puterea ta de a-ți transforma visele în realitate. Nu lăsa pe nimeni să-ți spună că nu poți, ci arată-le că poți."},
                {"Fii motivat de scopul tău și ai încredere în puterea ta de a-ți atinge viziunea. Rămâi concentrat și dedicat și vei vedea rezultatele pe care le dorești."},
                {"Fii motivat de visurile tale și ai încredere în puterea ta de a le transforma în realitate. Crede în tine necondiționat."},
                {"Fii optimist și ai încredere în puterea ta de a găsi soluții în fața oricărei provocări."},
                {"Fii optimist și ai încredere în puterea ta de a găsi soluții în fața oricărei provocări."},
                {"Fii optimist și ai încredere în puterea ta de a vedea oportunități în fiecare situație. Privește înainte cu speranță și vei găsi căile de a-ți îndeplini visurile."},
                {"Fii perseverent în fața obstacolelor și nu renunța la visurile tale."},
                {"Fii perseverent și ai încredere în puterea ta de a-ți atinge scopurile. Chiar și atunci când pare că drumul este dificil, nu renunța și mergi mai departe."},
                {"Fii perseverent și ai încredere în puterea ta de a-ți atinge scopurile. Succesul vine celor care nu renunță."},
                {"Fii perseverent și nu renunța la visele tale."},
                {"Fii pregătit să-ți asumi responsabilitatea pentru deciziile tale și să accepți că drumul ales poate fi plin de provocări și învățăminte."},
                {"Fii proactiv și ai încredere în puterea ta de a influența direcția vieții tale. Nu aștepta să se întâmple, fă ca lucrurile să se întâmple."},
                {"Fii recunoscător pentru că ai puterea de a influența viețile altor oameni. Nu subestima impactul pe care îl poți avea."},
                {"Fii recunoscător pentru fiecare nouă zi și pentru oportunitățile pe care le aduce. Ai puterea de a face din fiecare zi una extraordinară."},
                {"Fii recunoscător pentru fiecare nouă zi. Ai puterea de a face o diferență și de a trăi în plinătate."},
                {"Fii recunoscător pentru fiecare zi și pentru binecuvântările pe care le ai în viață. Ai puterea de a aprecia lucrurile mici și de a găsi bucurie în cele mai simple aspecte ale vieții."},
                {"Fii recunoscător pentru fiecare zi și pentru oportunitățile pe care le aduce."},
                {"Fii recunoscător pentru fiecare zi și pentru oportunitățile pe care le aduce. Ai puterea de a face ca fiecare zi să conteze și să aducă ceva pozitiv în viața ta și a celor din jurul tău."},
                {"Fii recunoscător pentru fiecare zi și vei descoperi că ai puterea de a găsi bucurie chiar și în cele mai mici lucruri."},
                {"Fii recunoscător pentru învățămintele pe care le primești în călătoria ta. Ai puterea de a învăța din experiențele tale și de a crește ca persoană în fiecare zi."},
                {"Fii recunoscător pentru învățămintele pe care le-ai primit în călătoria ta. Ai puterea de a crește și de a te transforma într-o persoană mai bună."},
                {"Fii recunoscător pentru lecțiile învățate din eșecuri și obstacole."},
                {"Fii recunoscător pentru lecțiile învățate din greșeli. Ele te fac mai puternic și te îndrumă pe calea succesului."},
                {"Fii recunoscător pentru lecțiile pe care le înveți în călătoria ta. Ele îți dezvăluie puterea interioară și te fac mai înțelept."},
                {"Fii recunoscător pentru obstacolele pe care le-ai învins. Ele sunt doar dovezi ale puterii și rezilienței tale."},
                {"Fii recunoscător pentru persoanele din viața ta care te susțin și te inspiră. Ai puterea de a forma legături puternice și de a aduce bucurie în viața celorlalți."},
                {"Fii recunoscător pentru propria ta călătorie și pentru toate experiențele care te-au adus până aici. Ai puterea de a crea un viitor strălucit bazat pe învățămintele trecutului."},
                {"Fii recunoscător pentru propria ta putere interioară și ai încredere în capacitatea ta de a înfrunta orice provocare. Nu subestima niciodată resursele pe care le ai în tine."},
                {"Fii recunoscător pentru provocările pe care le întâlnești. Ele îți dezvăluie puterea interioară și te fac mai rezistent."},
                {"Fii recunoscător pentru resursele pe care le ai și ai încredere în puterea ta de a le folosi în beneficiul tău și al altora."},
                {"Fii recunoscător pentru resursele pe care le ai și ai încredere în puterea ta de a le utiliza în mod eficient. Nu subestima nicio resursă, deoarece fiecare poate juca un rol important în atingerea obiectivelor tale."},
                {"Fii recunoscător pentru tot ceea ce ai și arată apreciere față de ceilalți."},
                {"Fii recunoscător pentru tot ceea ce ai și vei descoperi că ai o putere interioară de a te bucura de fiecare zi."},
                {"Fii respectuos cu tine însuți și cu propriile tale nevoi și limite."},
                {"Fii responsabil față de mediu și protejează natura."},
                {"Fii responsabil și asumă-ți consecințele propriilor acțiuni."},
                {"Fii stăpânul propriei tale destinări. Ai puterea de a lua decizii care îți vor aduce fericirea și împlinirea."},
                {"Fii stăpânul propriei tale vieți. Ai puterea de a lua decizii care să te ducă spre succes și împlinire."},
                {"Fii sursa propriei tale motivații. Ai puterea de a-ți cultiva o mentalitate pozitivă și de a-ți depăși temerile."},
                {"Fii un ascultător activ și oferă sprijin emoțional celor care au nevoie de el."},
                {"Fii un ascultător atent și oferă suport emoțional celor din jur."},
                {"Fii un bun comunicator și manifestă empatie în relațiile tale."},
                {"Fii un cetățean responsabil și respectă legile și valorile societății."},
                {"Fii un exemplu de bunătate și respect pentru cei mai tineri."},
                {"Fii un lider empatic și inspirător, care își sprijină echipa și o încurajează să crească și să se dezvolte."},
                {"Fii un lider inspirator și încurajează pe ceilalți să-și atingă potențialul."},
                {"Fii un mentor și oferă îndrumare celorlalți în căutarea lor de creștere și succes."},
                {"Fii un model de integritate și acționează în conformitate cu valorile tale."},
                {"Fii un model pozitiv pentru cei din jurul tău prin comportamentul tău."},
                {"Fii un prieten devotat și sprijinător."},
                {"Fii un prieten empatic și ascultă nevoile celorlalți."},
                {"Gândește-te la toate realizările tale trecute și la cât de puternic ai fost pentru a le atinge. Aceeași putere este în tine și acum."},
                {"Îmbrățișează schimbarea și ai încredere în puterea ta de a te adapta și de a-ți depăși limitele."},
                {"Încrederea în sine este cheia succesului. Ai puterea de a-ți construi încrederea și de a-ți atinge potențialul maxim."},
                {"Înțelepciunea vine din învățare și experiență. Ai puterea de a crește și de a te transforma într-o persoană mai bună în fiecare zi."},
                {"Învață din greșeli și folosește-le ca oportunități de creștere."},
                {"Lasă-ți pasiunea să te conducă și vei descoperi o energie și o putere pe care nu ți-ai imaginat-o vreodată."},
                {"Manifestă compasiune față de toate ființele vii."},
                {"Manifestă iertare și lasă trecutul în urmă pentru a te concentra pe prezent."},
                {"Manifestă încredere în propriile tale abilități și nu te descuraja de critici sau eșecuri temporare."},
                {"Manifestă loialitate față de cei dragi și fii de încredere."},
                {"Manifestă modestie în fața succesului și fii recunoscător pentru sprijinul celor din jur."},
                {"Manifestă modestie și evită să te lauzi sau să judeci pe alții."},
                {"Manifestă răbdare și înțelegere în relațiile tale personale și profesionale."},
                {"Manifestă răbdare și înțelegere în relațiile tale."},
                {"Manifestă recunoștință față de micile bucurii ale vieții."},
                {"Manifestă recunoștință față de succesul celorlalți și încurajează-i în reușitele lor."},
                {"Manifestă recunoștință pentru succesul și realizările tale, indiferent cât de mici sunt."},
                {"Manifestă recunoștință pentru viața pe care o trăiești și pentru oportunitățile primite."},
                {"Manifestă respect și compasiune față de toate ființele vii și protejează mediul înconjurător."},
                {"Manifestă sinceritate și transparență în comunicare."},
                {"Manifestă toleranță față de diversitatea culturală și ideologică."},
                {"Manifestă umor și bucurie în viața ta și în relațiile tale."},
                {"Nu lăsa critica să-ți afecteze încrederea în tine. Ai puterea de a-ți accepta imperfecțiunile și de a-ți urma propria taie."},
                {"Nu lăsa frica să-ți limiteze potențialul. Ai puterea de a depăși temerile și de a-ți îndeplini visurile."},
                {"Nu lăsa greutățile să te definească. Ai puterea de a te ridica și de a-ți crea propriul succes."},
                {"Nu lăsa îndoiala să-ți afecteze încrederea. Ai puterea de a crede în tine și de a-ți atinge potențialul maxim."},
                {"Nu lăsa obstacolele să-ți învingă spiritul. Ai puterea de a depăși orice situație și de a-ți crea propriul succes."},
                {"Nu lăsa trecutul să-ți definească viitorul. Ai puterea de a crea o nouă poveste pentru tine însuți."},
                {"Nu renunța la visele tale pentru că pare că este greu. În spatele fiecărui obstacol se ascunde o mare putere."},
                {"Nu subestima niciodată puterea ta de a face o schimbare pozitivă în lumea din jurul tău. Fii sursa de inspirație pentru ceilalți."},
                {"Nu subestima niciodată puterea unui zâmbet. Poți schimba lumea cu gesturi mărunte și cuvinte de încurajare."},
                {"Nu te compara cu alții, ci îți amintește că ai propriile tale talente și calități unice. Ai puterea de a străluci în propria ta lumină."},
                {"Nu-ți subestima niciodată impactul asupra altor oameni. Fiecare gest de bine și cuvânt de încurajare poate schimba vieți."},
                {"Perseverența este cheia succesului. Ai puterea de a merge mai departe chiar și atunci când pare că totul se prăbușește."},
                {"Promovează egalitatea și justiția în toate aspectele vieții."},
                {"Promovează iubirea și îngrijirea față de familie și prieteni."},
                {"Promovează pacea și evită conflictele inutile."},
                {"Respiră adânc și amintește-ți că ai puterea de a înfrunta orice provocare în calea ta."},
                {"Să-ți descoperi adevărata putere înseamnă să-ți depășești propriile limite și să crezi în tine necondiționat."},
                {"Tratează pe ceilalți cu respect și compasiune."},
                {"Ai puterea de a alege cum să reacționezi în fața provocărilor. Alege să fii puternic și rezistent."}



        };


        // Generare index aleatoriu
        Random random = new Random();
        int linie = random.nextInt(lista.length);
        int coloana = random.nextInt(lista[linie].length);

        //
        txt.setText(lista[linie][coloana]);
    }
}




