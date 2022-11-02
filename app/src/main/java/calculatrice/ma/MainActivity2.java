package calculatrice.ma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity2 extends AppCompatActivity {

    private SwitchCompat switchc;
    private EditText description ;
    private TextView display;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.description = findViewById(R.id.description);
        this.description.setShowSoftInputOnFocus(false);
        this.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(description.getText().toString().equals(getString(R.string.display))){
                    description.setText("");
                }
            }
        });

        this.display = findViewById(R.id.result);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucalcul,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.Item_standard:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.Item_scientifique:
                break;
        }
        return true;
    }

    public void updateTEXT(String valTOADD){
        String oldVAL = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        if(oldVAL.equals(getString(R.string.display))){
            description.setText(valTOADD);
            this.description.setSelection(cursorPosision + 1);
        }else {

            String leftVal = oldVAL.substring(0, cursorPosision);
            String rightVal = oldVAL.substring(cursorPosision);
            this.description.setText(String.format("%s%s%s", leftVal, valTOADD, rightVal));
            this.description.setSelection(cursorPosision + 1);
        }


    }






    public void zeroBTN(View view){
        this.updateTEXT("0");
        getResult();
    }
    public void oneBTN(View view){
        this.updateTEXT("1");
        getResult();
    }
    public void twoBTN(View view){
        this.updateTEXT("2");
        getResult();
    }
    public void threeBTN(View view){
        this.updateTEXT("3");
        getResult();
    }
    public void fourBTN(View view){
        this.updateTEXT("4");
        getResult();
    }
    public void fiveBTN(View view){
        this.updateTEXT("5");
        getResult();
    }
    public void sixBTN(View view){
        this.updateTEXT("6");
        getResult();
    }
    public void sevenBTN(View view){
        this.updateTEXT("7");
        getResult();
    }
    public void eightBTN(View view){
        this.updateTEXT("8");
        getResult();
    }
    public void nineBTN(View view){
        Toast.makeText(MainActivity2.this,"gg",Toast.LENGTH_LONG);
        this.updateTEXT("9");
        getResult();

    }
    public void pointBTN(View view){
        this.updateTEXT(".");
    }
    public void addBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-') {
            this.updateTEXT("+");
        }

    }
    public void subBTN(View view){

        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if( c!='-') {
            this.updateTEXT("-");
        }

    }
    public void multBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-') {
            this.updateTEXT("×");
        }

    }
    public void divBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-') {
            this.updateTEXT("÷");
        }
    }
    public void ereaseBTN(View view){
        this.description.setText("");
        this.display.setText("");

    }
    public void signeBTN(View view){
        this.updateTEXT("-");
    }
    public void equalBTN(View view){
        String actualResult = display.getText().toString();
        description.setText(actualResult);
    }
    public void getResult(){
        String expression =  description.getText().toString();

        expression=expression.replaceAll("÷","/");
        expression=expression.replaceAll("×","*");
        expression=expression.replaceAll("√\\(","sqrt(");
        expression=expression.replaceAll("π","pi");

        Expression exp = new Expression(expression);
        String result = String.valueOf(exp.calculate());
        if(!result.equals("NaN")){
            display.setText(result);
        }
    }

    public void powerBTN(View view){
        this.updateTEXT("^");
        getResult();
    }
    public void racineBTN(View view){
        this.updateTEXT("√(");
        int cursorPosision = this.description.getSelectionStart();
        this.description.setSelection(cursorPosision + 1);

    }
    public void moduloBTN(View view){
        this.updateTEXT("%");
        getResult();
    }
    public void clearBTN(View view){

        String oldVAL = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();

        if(oldVAL.length()!=0 && cursorPosision!=0){
            /* String leftVal = oldVAL.substring(0, cursorPosision-1);
            String rightVal = oldVAL.substring(cursorPosision);
            this.description.setText(String.format("%s%s", leftVal, rightVal));*/

            SpannableStringBuilder selection = (SpannableStringBuilder) description.getText();
            selection.replace(cursorPosision-1,cursorPosision,"");
            description.setText(selection);
            this.description.setSelection(cursorPosision - 1);
            getResult();
        }

    }
    public void paranBTN(View view){
        int cursorPosision = this.description.getSelectionStart();
        String VAL = this.description.getText().toString();
        int leftpar = 0;
        int rightpar = 0;
        int ln = description.length();
        for (int i=0;i<ln;i++){
            if(VAL.charAt(i) == '('){
                leftpar++;
            }
            if(VAL.charAt(i) == ')'){
                rightpar++;
            }
        }

        char c = VAL.charAt(cursorPosision-1 );
        if( leftpar == rightpar || c == '('){
            this.updateTEXT("(");
        }else {
            this.updateTEXT(")");
            getResult();
        }

    }

    public void cosBTN(View view){
        this.updateTEXT("cos(");
        int cursorPosision = this.description.getSelectionStart();

        this.description.setSelection(cursorPosision + 3);
        getResult();
    }
    public void sinBTN(View view){
        this.updateTEXT("sin(");
        int cursorPosision = this.description.getSelectionStart();
        this.description.setSelection(cursorPosision + 3);

    }
    public void tanBTN(View view){
        this.updateTEXT("tan(");
        int cursorPosision = this.description.getSelectionStart();

        this.description.setSelection(cursorPosision + 3);

    }
    public void piBTN(View view){
        this.updateTEXT("π");
        int cursorPosision = this.description.getSelectionStart();

        this.description.setSelection(cursorPosision + 1);
        getResult();
    }
    public void lnBTN(View view){
        this.updateTEXT("ln(");
        int cursorPosision = this.description.getSelectionStart();
        this.description.setSelection(cursorPosision + 2);

    }
    public void expBTN(View view){
        this.updateTEXT("exp(");
        int cursorPosision = this.description.getSelectionStart();

        this.description.setSelection(cursorPosision + 3);

    }
    public void eBTN(View view){
        this.updateTEXT("e");
        getResult();
    }
    public void factBTN(View view){

        this.updateTEXT("!");
        getResult();
    }


}