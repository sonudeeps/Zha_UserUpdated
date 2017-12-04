package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment_1 extends Fragment {
    Button English_Button, Tamil_Button;
    TextView Profile_Textview;
    String lang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v =  inflater.inflate(R.layout.fragment_1, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");
        English_Button = (Button) v.findViewById(R.id.English_Button);
        Tamil_Button = (Button) v.findViewById(R.id.Tamil_Button);
        Profile_Textview = (TextView) v.findViewById(R.id.Profile_Textview);
        Tamil_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile_Textview.setText("கடந்துவந்த பாதை வரலாற்றைத் திருத்தி எழுதுகிறார் தமிழக வாழ்வுரிமைக் கட்சித் தலைவர்! உலகை விவரிப்பது, விமர்சிப்பது என்பதல்ல தேவைப்படுவது; மாறாக, உலகை மாற்றுவதுதான் செய்ய வேண்டியது” என்கிறார் மாமேதை காரல் மார்க்ஸ். மார்க்ஸைப் பற்றி அவர் நண்பர் ஏங்கல்ஸ் கூறுகிறார் “மனிதனுக்கு முதலில் உணவு வேண்டும்; தண்ணீர் வேண்டும்; உறைவிடம் வேண்டும்.இவைதான் அரசியல், அறிவியல், ஆன்மீகம், கலை என்றெல்லாம் மனிதன் ஈடுபடுவதற்கு முன்நிபந்தனை. வெளிப்படையான இந்த எளிய உண்மையை, அரிய பெரிய சித்தாந்தங்கள் என்னும் ஆகாத களைகள் புதராய் மண்டி மறைத்திருந்தன. அவற்றை அகற்றி அப்புறப்படுத்தியவர்தான் காரல் மார்க்ஸ்!”\n" +
                        "இப்போது புரிந்திருக்கும் உங்களுக்கு, செய்ய வேண்டியது என்னவென்று! இந்தியா என்று உருவாக்கி 70 ஆண்டுகளாகியும் இன்னும் நமது அடிப்படை தேவைகளையே அடையாத நிலை!\n" +
                        "வாழ்வுரிமையே கேள்விக்குறியான நிலை!\n" +
                        "இதுதான் நமது வரலாறாகத் தொடர வேண்டுமா?\n" +
                        "“கூடாது; இதோ நானிருக்கிறேன்; வரலாற்றைத் திருத்தி எழுதிக் காட்டுகிறேன்” என்று கடலூர் மாவட்டம் புலியூரிலிருந்து இளம்புயலாகப் புறப்பட்டவர்தான் பண்ருட்டி தி.வேல்முருகன்; தமிழக வாழ்வுரிமைக் கட்சியின் நிறுவனத் தலைவர்.\n" +
                        "பாட்டாளி மக்கள் கட்சியின் அருங்கொடைதான், பெருங்கனவைச் சுமந்தபடி அரசியல் வானில் சிறகடிக்கும் இந்த அருமைத் தமிழ் இளவல்.\n" +
                        "2001 மற்றும் 2006 தேர்தல்களில் பண்ருட்டி தொகுதியில் நின்று வென்று பாட்டாளி மக்கள் கட்சி எம்எல்ஏவாக இருமுறை சட்டமன்றம் சென்றவர்.\n" +
                        "இருமுறையும் திறம்படப் பணி செய்து மக்கள் மனதில் நிற்பவர்.\n" +
                        "சிறப்புறப் பணி செய்த “இளம் சட்டமன்ற உறுப்பினர்” என்று, தமிழக மேனால் ஆளுனர் திரு பீஷ்ம நாராயணசிங் அவர்களின்  கையால் “பாரத ஜோதி” விருதினைப் பெற்றவர்.\n" +
                        "இளம் வயதிலேயே சட்டமன்ற “உறுதிமொழி குழு தலைவராக” தேர்வானவர்.\n" +
                        "உறுதிமொழி குழு தலைவராக சிறப்பாகப் பணிபுரிந்தார். தமிழ்நாடெங்கும் பயணித்து, அரசின் திட்டப்பணிகளை ஆராய்ந்தார்; அவற்றின் குறை நிறைகளை விவாதித்து உரிய முடிவினை அரசுக்குப் பரிந்துரைத்தார்.\n" +
                        "எம்எல்ஏவாக யாரும் இதுவரை செய்யாத, சவாலான பல பணிகளையும் செய்து முடித்த வேல்முருகன், பாமகவை விட்டே வஞ்சகத்தால் விலக்கப்பட்டார்.\n" +
                        "அதன்பின் 2012 ஜனவரி 14, தைப்பொங்கலன்று தமிழக வாழ்வுரிமைக் கட்சியைத் தொடங்கினார்.\n" +
                        "தமிழக மக்களின் தன்மானம், இனமானம், மொழியுரிமை, வாழ்வுரிமை காக்கும் அரண் தமிழக வாழ்வுரிமை கட்சி!\n" +
                        "தமிழக வாழ்வுரிமைக் கட்சியின் வழி நின்று தமிழக வரலாற்றையே திருத்தி எழுதும் திருப்பணியில் ஈடுபட்டுள்ளார் அதன் நிறுவனத் தலைவர் பண்ருட்டி தி.வேல்முருகன்.\n" +
                        "மணல் குவாரிகளை அரசுடைமையாக்குதல்,மழை நீர் சேமிப்புத் திட்டம்,போன்ற எண்ணற்ற வரலாற்று சிறப்புமிக்க திட்டங்களுக்கு சட்டமன்றத்தில் குரல் கொடுத்து  அவை நிறைவேற காரணமாக இருந்தவர், மேலும் தமிழர்களின் கலை, கலாசாரம்,பண்பாடு,தொழிலாளர் நலன்,விவசாயிகள் நலன்,பள்ளி,கல்லூரி,மாணவர்கள்,நலன்,ஆகியவற்றுக்காகவும் பத்தாண்டு காலம் சட்டமன்றத்தில் தொடர்ந்து குரல் எழுப்பியவர். \n" +
                        "சட்டமன்ற உறுப்பினராக அவர் ஆற்றிய குறிப்பிடத்தக்க பணிகளுள் ஒன்று, பத்தாண்டு கால சிறைக்கைதிகள் பலர் விடுதலை பெற்று புதுவாழ்வைத் தொடங்க உதவியதாகும்.\n" +
                        "இதற்காக, பேரறிஞர் அண்ணா அவர்களின் பிறந்தாளை (செப்டம்பர் 15) முன்னிட்டு, பத்தாண்டுகள் சிறைவாசம் முடித்த அத்தனைக் கைதிகளையும் விடுதலை செய்ய வேண்டும் என்ற கோரிக்கையை அரசிடம் முன்வைத்தார்.\n" +
                        "சிறைச்சாலை என்பது தப்பு செய்தவரை தண்டிக்கும் இடம் என்றால், அந்த தண்டனை என்பது அவரை சீர்திருத்துவதுதான்; அப்படி சீர்திருத்த, 10 ஆண்டுகள் சிறைவாசமே அதிகபட்சம்; அதன் பிறகு எந்தக் கைதியையும் விடுவித்துவிட வேண்டும் என்பதே இந்தக் கோரிக்கையின் நோக்கமாகும்.\n" +
                        "இந்தக் கோரிக்கையை 2007ல் திமுக அரசிடம் வைத்து, 7 ஆண்டுகால சிறைக்கைதிகளைக்கூட விடுதலை செய்ய வைத்தார் வேல்முருகன்.\n" +
                        "அதே கோரிக்கையை தமிழக வாழ்வுரிமைக் கட்சியின் தலைவராக 13-09-2015ல் அதிமுக அரசிடமும் முன்வைத்தார். அதோடு, திருச்சி மற்றும் செய்யாறு சிறப்பு முகாம்களில் உள்ள ஈழத் தமிழர்கள் 18 பேரையும் அண்ணா பிறந்த நாளில் விடுதலை செய்ய வேண்டும் என்ற கோரிக்கையையும் இணைத்திருந்தார்.\n" +
                        "இலங்கையில் சிங்களப் பேரினவாத இனப்படுகொலையினின்றும் தப்பித்து படுகாயங்களுடன் உயிருக்கு ஆபத்தான நிலையில் தமிழகத்திற்கு அடைக்கலமாக வந்த ஈழத் தமிழர்களைத்தான் வழக்குகளைப் போட்டு இப்படி சிறப்பு முகாம்களில் அடைத்திருக்கிறது தமிழக அரசு.\n" +
                        "இந்த சிறப்பு முகாம்களை இழுத்து மூட வலியுறுத்தி திருச்சியில் மாபெரும் அறப் போராட்டத்தை முன்னெடுத்தது தமிழக வாழ்வுரிமைக் கட்சி. பல்லாயிரம் பேர் திரண்ட அந்தப் போராட்டத்திற்கு தலைவர் வேல்முருகனே தலைமை தாங்கினார்.\n" +
                        "இது, தமிழக அரசியல் கட்சிகள் மற்றும் தமிழர் இயக்கங்களுக்கு முன்மாதிரியாக அமைந்து, அவர்களும் இந்த சிறப்பு முகாம்களை மூட வலியுறுத்தி போராட்டம் நடத்தினர். உலகின் கவனத்தையே ஈர்த்தன இந்தப் போராட்டங்கள்.\n" +
                        "தமிழகத்தைச் சுரண்ட நடுவண் அரசு செய்திருக்கும் பல்வேறு ஏற்பாடுகளில் ஒன்று நெடுஞ்சாலைச் சுங்கச்சாவடிகள்!\n" +
                        "இதனைக் கண்டித்தும் களமிறங்கியது தமிழக வாழ்வுரிமைக் கட்சி.\n" +
                        "தமிழகம் முழுவதும் 60 சுங்கச்சாவடிகளை அமைத்து பகல் இரவு பாராது தொடர் வழிப்பறிக் கொள்ளையே நடத்தப்படுகிறது. \n" +
                        "சுங்கக் கட்டணம் என்ற பெயரில் வசூல் வேட்டை நடத்தும் இந்த நவீன மோசடியைத் தோலுரித்ததுடன், நடுவண் அரசை, சுங்கக் கட்டணம் தொடர்பாக வெள்ளை அறிக்கையையே வெளியிட வலியுறுத்தியது வாழ்வுரிமைக் கட்சி.\n" +
                        "சுங்க சாவடிகளில் கட்டணம் செலுத்த மறுக்கும் \"ஒத்துழையாமை\" போராட்டமும் நடத்தப்பட்டது. இதில் பெண்கள், தாய்மார்கள் உள்ளிட்ட வாழ்வுரிமைக் கட்சித் தொண்டர்கள் கைதாகி சிறை செல்ல நேரிட்டது.\n" +
                        "சுங்கச்சாவடிகளில் ஆண்டுதோறும் ஏப்ரல், செப்டம்பர் மாதங்களில் கட்டணத்தை படாப்பழியாக உயர்த்துவது வாடிக்கையாக இருக்கிறது. \n" +
                        "இதை எதிர்த்து தமிழகம் முழுவதும் உள்ள சுங்கச் சாவடிகளை அகற்றுகிற மாபெரும் முற்றுகைப் போராட்டங்களை இரண்டு முறை நடத்திய தமிழக வாழ்வுரிமைக் கட்சி, இப்போராட்டம் சுங்கச்சாவடிகளை அகற்றும் வரை தொடரும் என அறிவித்துள்ளது.\n" +
                        "நெய்வேலி லிக்னைட் கார்ப்பொரேஷன் (என்,எல்,சி) மற்றும் அதன் தொழிலாளர்கள் மீது மிகவும் அக்கறை கொண்டவர் வேல்முருகன்.\n" +
                        "என்எல்சிக்கு தங்கள் வாழ்விடம் மற்றும் விளைநிலங்களை வழங்கியவர்கள் அதனைச் சுற்றியுள்ள சுமார் 40 கிராமங்களைச் சேர்ந்த அவரது சொந்த கடலூர் மாவட்ட மக்களே.\n" +
                        "ஆனால் அவர்களுக்கு என்எல்சியில் வேலை தரப்படும்; கிராமங்களுக்கு சாலைப் போக்குவரத்து வசதி, மின் வசதி, குடிநீர் வசதி, கல்வி நிலைய வசதி என அடிப்படை வாழ்வாதார வசதிகள் ஏற்படுத்தித் தரப்படும் என்று வாக்களித்திருந்த என்எல்சி நிர்வாகம் இன்றுவரை அதனை சரிவர நிறைவேற்றவில்லை.\n" +
                        "என்எல்சி சுரங்கங்களில் ஒப்பந்தத் தொழிலாளர்களாக சேர்த்துக் கொள்ளப்பட்ட சுமார் 13 ஆயிரம் பேரை, உச்ச நீதிமன்றமே தீர்ப்பளித்தும்கூட இன்றுவரை பணி நிரந்தரம் செய்யவில்லை. 25 ஆண்டுகளாக ஒப்பந்தக் கூலிகளாகவே உள்ளனர் அவர்கள்.\n" +
                        "என்எல்சியின் இந்த நியாயமற்ற போக்கினைக் கண்டித்து கடந்த 20 ஆண்டுகளுக்கும் மேலாக போராடி வருகிறார் வேல்முருகன். என்எல்சியில் சிறு அளவிலான பிரச்சனை ஏற்பட்டாலும் ஒடோடிச் சென்று தொழிலாளர்களுக்குத் தோள் கொடுப்பவராக இருக்கிறார் அவர்.\n" +
                        "என்எல்சியைப் பொறுத்தவரை மிகவும் ஆபத்தான பிரச்சனையாக உருவெடுத்திருப்பது “வெளிமாநிலத்தவர் ஆதிக்கம் மற்றும் நுழைப்பு” ஆகும். இதற்கு முடிவுகட்ட முனைப்புடன் செயல்பட்டுக் கொண்டிருக்கிறார் வேல்முருகன்.\n" +
                        "முன்னாள் பிரதமர் ராஜீவ் காந்தி கொலை வழக்கில் 27 ஆண்டுகளாக சிறையில் உள்ளனர் முருகன், சாந்தன், பேரறிவாளன், ரவிச்சந்திரன், ராபர்ட் பயஸ், ஜெயக்குமார், நளினி ஆகிய ஏழு தமிழர்கள். இவர்களை விடுதலை செய்ய தொடர்ந்து முயற்சி மேற்கொண்டிருப்பவர் வேல்முருகன்.\n" +
                        "அதன் பயனாக அவர்களை விடுதலை செய்வதாக அறிவித்தார் மறைந்த முன்னாள் முதல்வர் ஜெயலலிதா. ஆனால் முந்தைய, இன்றைய நடுவண் அரசுகள் முட்டுக்கட்டை போட, அவர்களின் சிறைவாசம் நீடிக்கிறது. இதை உடைத்தெறிய சட்டப்படியும் தார்மீகப்படியும் போராடி வருகிறார் வேல்முருகன்.\n" +
                        "சிங்களக் கடற்படையால் தமிழக மீனவர்கள் தாக்கப்படுவது தொடர்கிறது. இதுவரை 600 பேர் கொல்லப்பட்டனர். 1,000 பேர் படுகாயமடைந்தனர். படகுகள், வலைகள் அழிக்கப்பட்டு பலநூறு கோடிகள் இழப்பு – இதற்கு இந்திய அரசும்தான் பொறுப்பு. ஏனெனில் கடற்தொழிலையும் கார்ப்பொரேட்டுகளிடம் ஒப்படைக்க மோடி அரசு முடிவு செய்திருக்கிறது.\n" +
                        "இந்நிலையில் தமிழக மீனவரின் பாரம்பரிய தொழில் உரிமையைக் காக்க அவர்களோடு ஒன்றிணைந்து நிற்கிறார் வேல்முருகன்.\n" +
                        "2009-ஈழ இனப்படுகொலைக்கு நீதி கேட்டு போராடிக் கொண்டிருக்கும் உலகத் தமிழர்களோடும் ஒன்றி நிற்கிறார். \n" +
                        "கூலித் தொழில் தேடி ஆந்திராவுக்குச் சென்ற 20 அப்பாவி பழங்குடித் தமிழர்களை செம்மரம் வெட்டியவர்கள் எனக் கூறி கடத்திச் சித்திரவதை செய்து சுட்டுப் படுகொலை செய்தது ஆந்திர காவல்துறை.\n" +
                        "இதை தமிழக அரசு, நடுவண் அரசு, இரண்டுமே கேட்க முன்வராத நிலையில், வாழ்வுரிமைக் கட்சித் தலைவர் வேல்முருகன், மனித உரிமை மற்றும் தமிழ் உரிமை அமைப்புகளோடு இணைந்து நீதி கேட்கும் போராட்டத்தை முன்னெடுத்தார். இதுவும் இனப்படுகொலைதான் என சிபிஐ விசாரணை வேண்டும் என்கிறார்.\n" +
                        "அண்டை மாநிலங்களுடனான காவிரி, முல்லைப் பெரியாறு, பாலாறு, பவானியாறு, நெய்யாறு போன்ற நதி நீர் பிரச்சனைகளில் தமிழகத்தின் உரிமையை விட்டுத்தராது நிலைநாட்டும் போராட்டத்தினை தொய்வின்றித் தொடர்பவரும்தான் வேல்முருகன். \n" +
                        "கூடங்குளம் அணுவுலைப் பிரச்சனை தொடரும்போதே நெடுவாசல் மற்றும் கதிராமங்கலத்தில் ஹைட்ரோகார்பன், மீத்தேன், ஷேல் எடுக்கும் பணிகளைத் தொடங்கியது மோடி அரசு. இதனால் அந்த ஊர்களில் ஏற்பட்ட தொடர் போராட்டங்களில் மக்களோடு இணைந்துள்ளார் வேல்முருகன்.\n" +
                        "காவிரி டெல்டா மாவட்டங்களை “பாதுகாக்கப்பட்ட வேளாண் மண்டலமாக” அறிவிக்கக் கோரி போராட்டம் ஒருபுறம் நடந்துகொண்டிருக்கும் அதேவேளை கடலூர், நாகை, அரியலூர் மாவட்டங்களில் 45 ஊர்களை உள்ளடக்கிய பல்லாயிரம் ஏக்கர் பெட்ரோலிய மண்டலத்தை அறிவித்தார் மோடி. இதை தடுத்து நிறுத்துவோம் என சூளுரைத்திருக்கிறார் வேல்முருகன்.\n" +
                        "அண்மையில் நீட், ஜிஎஸ்டி, தமிழக அரசுப் பணி மற்றும் தமிழகத்திலுள்ள நடுவண் அரசுப் பணிகளில் வெளிமாநிலத்தவர் – வெளிநாட்டவர் ஆக்கிரமிப்பு போன்ற பிரச்சனைகள் எழும்ப, அவற்றையும் எதிர்கொண்டார்.\n" +
                        "தொய்வும் ஓய்வுமில்லா தன் போராட்டத்தால் தமிழக வரலாற்றைத் திருத்தி எழுதிக் கொண்டிருக்கிறார் தமிழக வாழ்வுரிமைக் கட்சித் தலைவர்.\n" +
                        "\n");
            }
        });
        English_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile_Textview.setText("Our leader Carl Marcus transforms the path of travellingby ecstatically statingthat ”Defining and Judging the world about the problems are superfluous;Inchange to that a complete diverse transition is the need of the hour”. Mr.Aengles, friend of Marcus says “The basic needs of a man are to have food, water and place to stay. These are the elements which enhance the politics, science, spirituality and art of human and survival. This transparent truth has been made as hindrance to understand by some antagonists. The one who removed the obstacles in understanding is Carl Marcus. ”India started developing since 70 years, but still it hasn’t attained the state  to serve all the people of the nation with these basic needs. \n" +
                        "So here I come to change the history” says Mr.T.Velmurugan ,Founder and leader (TamizhagaVazhvurimaikatchi) .He has been elected as the M.L.A of PMK in the years of 2001 and 2006 in Pandrooti and attended the Legislative Assembly as the representative. He did an excelling service during his period and bagged the award “Bharat Jyothi”(Young Legislative assembly member) from the hands of Former Governor Mr.BhishmaNarain Singh.He has been elected as the Leader of Pledge Committee during his young age and performed well.He travelled all over TamilNadu to explore the projects and deeds of the government and he analysed and suggested the good and bad briefly to the government. He served well as an M.L.A but performing many challenging works. But he has been dismissed by PMK  .\n" +
                        "On  14th January2012. on the day of TamilPongal festival he started the TVK party. He stated that he will serve for the self-esteem, linguistic rights, ethnicity and livelihood rights of Tamil people. He has also raised his voice in the assembly to make the sand quarrying as state owned property, rain water harvesting and various other special projects. He has been the main reason for satisfying those projects.Morevover, he also continued to talk in the Legislative Assembly for arts, culture, culture, labour, farmers welfare, school, college, students and welfare for a decade. One of the remarkable acts that he has done as a legislator is that many of the ten year prisoners have been relieved by the release to start their new living.For this purpose, he requested  the Government to release all the prisoners who had been imprisoned for decades, following the birth of Anna (September 15).If the prisons are punished for the wrongdoer, that punishment is to reform him; That is the 10 years imprisonment, the most promising, being said this claimed for the release of prisoners Mr.T. Velmurugan made this request to the DMK government in 2007 and released only seven years of imprisoned prisoners.\n" +
                        "The same request was presented to the AIADMK on 13-09-2015 as Chairman of the Tamil Livestock Party.In addition,On the birthday of Anna he requested to release the Eelam Tamils in Tiruchi and Cheyyar special camps which  included 18 people. Tamilnadu Government has prisoned the Eelam Tamils as refugees in Tamil Nadu in special camps ,who actually escaped from Sinhala chauvinist genocide in Sri Lanka as a refugee. The party did  massivemovement in Trichy insisted on dragging and pulling out of these special camps imprisoning.The leader T. Velmurugan presided as the head over the struggle of thousands of people.This stood as a model for the other Tamil political parties and Tamil movements to close these camps. The protests attracted the attention all over the world.One of the major change made by the Central Government to exploit Tamil Nadu is the highway customs duty .This was condemned by the Party. \n" +
                        "All over Tamil Nadu over 60 tollgates has been set up to monitor and get the duty amounts.The modern fraud, which is a hunt for the customs duty, has been lodged by the our Party for urging the Central Government to issue a White Paper on Customs Charges.The \"non-disobedience\" movement, which refuses to pay in customs offices, was also conducted. In this, Livelihood volunteers, including women and mothers, were arrested and imprisoned.It is compulsary to increase the bills in April and September every year in toll ticket sales.The Party has conducted two major sabotage struggles to remove the customs booths across Tamil Nadu, has announced that the struggle will continue until the removal of the customs duty. Mr. TVelmurugan is very much concerned about NeyvelliLignite Corporation (N, L, C) and its workers .NLC has been  provided with  habitat and farmland of theCuddalore district people  of about 40 villages surrounding it.The people will be employed at NLC.The NLC administration, which has voted to provide basic livelihood facilities for roads, transport facilities, electricity, water and education facilities, has not yet been fulfilled. In the NLC mines, about 13 thousand people who have been enlisted as contract labourers, have not yet been upheld  as permanent workers in the event of Supreme Court’s order. They have been a contract wage for 25 years.Mr.T Velmurugan is fighting for the last 20 years by denouncing this unfair practice of NLC.\n" +
                        "He has been a volunteer of the NLC's small-scale problem.As far as the NLC is concerned, it has become dangerous due to the other state dominance in the industry. Mr.TVelmurugan is acting with an intention to end this.There are seven Tamils - Murugan, Shanthan, Perarvallan, Ravichandran, Robert Paes, Jayakumar and Nalini - who are in jail for 27 years in the murder of former Prime Minister Rajiv Gandhi.Mr.TVelmurugan is the one who tried to release them.Finally, the former Chief Minister Jayalalithaa announced release of them. But earlier, today's federal governments stifle their imprisonment.Mr.TVelmurugan is struggling with the law and moral breakthrough for this cause.The Tamil Nadu fishermen continue to be attacked by the Sinhala navy. So far 600 people have been killed. 1,000 people were injured. The boats and nets have been destroyed and the loss of several hundred crores - for which the Indian state is equally responsible. Because the Modi government has decided to hand over fisheries to corporates.In this situation, Mr.TVelmurugan comes together to protect the tradition of the traditional Tamil Nadu fisheries.In 2009 he stands with the Tamil Tamils who are fighting justice for Eelam genocide.The Andhra Police shot and killed the 20 innocent Tamils who went to Andhra in search of a wage labourer.While the Tamil Nadu government and the federal government did not ask for this,PartyleaderMr.TVelmurugan forwarded a struggle for justice with human rights and Tamil rights organizations. \n" +
                        "The CBI should investigate that this is genocide. Mr. T Velmurugan struggles to ensure that Tamil Nadu does not get abandoned with  the rights of water in the river waters like Cauvery, MullaiPeriyar, Palar, Bavanaiyar and Neyyar with neighboring states.As the Koodankulam nuclear power problem continues, the Modi government has begun to take hydrocarbon, methane and shells in the Neduvasal and Karamatangala. Velmurugan joined the people in the series of struggles in the area.The struggle to declare Cauvery delta districts as a \"protected area of agriculture\" has been on the one hand, while Modi announced  thousands of acres petrol zone covering 45 villages in Cuddalore, Nagapattinam and Ariyalur districts. Velmurugan has vowed that we will stop this.Recently, Neet, GST, Government of Tamil Nadu and the state of the central government work in Tamil Nadu - the issue of other state workers,occupation and the problems that arose are all opposed by Mr.TVelmurugan. Party leader writes to transform and change the history of Tamil Nadu with his struggle.\n" +
                        "\n");
            }
        });
        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("தலைவரின் வாழ்க்கை குறிப்பு");
        }
        else
        {

            SampleActivity.toolbar.setTitle("Leader Bio");
        }

        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }

}