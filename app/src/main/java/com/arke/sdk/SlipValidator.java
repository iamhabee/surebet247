package com.arke.sdk;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arke.sdk.util.printer.Printer;
import com.usdk.apiservice.aidl.printer.ASCScale;
import com.usdk.apiservice.aidl.printer.ASCSize;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.HZScale;
import com.usdk.apiservice.aidl.printer.HZSize;
import com.usdk.apiservice.aidl.printer.OnPrintListener;

import org.json.JSONException;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;

public class SlipValidator extends AppCompatActivity {

    private Context context;

    private static final int FONT_SIZE_SMALL = 0;
    private static final int FONT_SIZE_NORMAL = 1;
    private static final int FONT_SIZE_LARGE = 2;
    private static final String DIR = "/storage/emulated/0/RukkabetAgent/assets/screenshots/";
    private static final String divider = "===========================================";
    private static final String single_divider = "***********************************";

    /**
     * Alert dialog.
     */
    private ProgressDialog progDialog;


    /**
     * Constructor.
     */
    SlipValidator(Context context) {
        this.context = context;
        this.progDialog = new ProgressDialog(context);
    }


    public void validateSlipThenPrint(String slipCode){
//        941725
//        String URL = "https://rukkabet.com/api/sports/find-coupon/"+slipCode;
//        RequestQueue requestQueue = Volley.newRequestQueue(this.context);

        this.progDialog.setMessage("Validating Slip...");
        this.progDialog.show();
        notifyUser();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                URL,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        if (progDialog.isShowing()) {
//                            progDialog.dismiss();
//                        }
//                        try {
//                            notifyUser(response);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (progDialog.isShowing()) {
//                            progDialog.dismiss();
//                        }
//                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("API RESPONSE", error.toString());
//                    }
//                }
//        );

//        requestQueue.add(jsonObjectRequest);
    }

    private void notifyUser(){
//        List<Selection> selections = JSON.parseArray(selectionArray, Selection.class);
                List<Selection> selections = Arrays.asList(new Selection[]{
                        new Selection("1", 1, 1, 1, "1", "football", "laliga", "league", "event", "start date", 1, "market name", "specifies", 1,  "odd name", "type", 2,"score", 1,  "settled", "created", "updated" )
//                        String id, int bet_id, int event_id, int provider_id, String element_id, String sport, String category, String tournament, String event, String start_date, int market_id, String market_name, String specifiers, int odd_id, String odd_name, String type, float odds, String score, int status, String settled_at, String created_at, String updated_at
                });

//                Selection slipSelection = null;

                String betslip_id = "id";
                String coupon_code = "Coupon code";
                String user_id = "user id";
                String odds = "2.5";
                String pot_winnings = "200";
                String bonus = "120";
                String stake = "222";
                String created_at = "created at";
                String bet_type = "get type";

                BettingStake slipInfo = new BettingStake();
                slipInfo.stakeDetails(betslip_id, coupon_code, user_id, bet_type, created_at, stake, odds, bonus, pot_winnings);

                // print slip
                printSlip(slipInfo, selections);
                Toast.makeText(this.context, "Betting slip  found ", Toast.LENGTH_SHORT).show();

    }

//    private void notifyUser(JSONObject response) throws JSONException {
//        switch (response.getString("message")){
//            case "found":
//                // handle slip response
//                // get coupon object from response
////                JSONObject coupon = new JSONObject(response.getString("coupon"));
//                // get array of selections from coupon
////                String selectionArray = coupon.getString("selections");
//                // get more details about the coupon
////                Selection slipSelection;
////
////                String betslip_id = coupon.getString("slip_id");
////                String coupon_code = coupon.getString("coupon_code");
////                String user_id = coupon.getString("user_id");
////                String odds = coupon.getString("odds");
////                String pot_winnings = coupon.getString("pot_winnings");
////                String bonus = coupon.getString("bonus");
////                String stake = coupon.getString("stake");
////                String created_at = coupon.getString("created_at");
////                String bet_type = coupon.getString("bet_type");
//
//
//                // create java object instance of the selection JSON object
////                List<Selection> selections = JSON.parseArray(selectionArray, Selection.class);
//                List<Selection> selections = Arrays.asList(new Selection[]{
//                        new Selection("1", 1, 1, 1, "1", "football", "laliga", "league", "event", "start date", 1, "market name", "specifies", 1,  "odd name", "type", 2.5f,"score", 1,  "settled", "created", "updated" )
//                });
//
//                Selection slipSelection = null;
//
//                String betslip_id = slipSelection.getId();
//                String coupon_code = "Coupon code";
//                String user_id = slipSelection.getId();
//                String odds = slipSelection.getOdd_name();
//                String pot_winnings = "Winnings";
//                String bonus = "Get bonus";
//                String stake = "Get stake";
//                String created_at = slipSelection.getCreated_at();
//                String bet_type = slipSelection.getType();
//
//                BettingStake slipInfo = new BettingStake();
//                slipInfo.stakeDetails(betslip_id, coupon_code, user_id, bet_type, created_at, stake, odds, bonus, pot_winnings);
//
//                // print slip
//                printSlip(slipInfo, selections);
////                try {
////
////                    Log.d("SlipValidator", new String(readAssetsFileStorage(DIR+"rukka_bet_logo.bmp")));
////                } catch (RemoteException e) {
////                    e.printStackTrace();
////                }
//                Toast.makeText(this.context, "Betting slip  found ", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                Toast.makeText(this.context, "Betting slip not found", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        Log.d("API RESPONSE", response.toString());
//    }


    private void printSlip(BettingStake slipInfo, List<Selection> selections) {

        // Show dialog
        showDialog(R.string.waiting_for_printing, false);
        try {
            // Get status
            Printer.getInstance().getStatus();
            Printer.getInstance().setPrnGray(5);

            // Add logo
//            Printer.getInstance().addImage(AlignMode.CENTER, readAssetsFileStorage(DIR+"rukka_bet_logo.png"));
            setFontSpec(FONT_SIZE_SMALL);
            Printer.getInstance().addText(AlignMode.CENTER, "License N. I6H3MO/148K20");
            Printer.getInstance().addText(AlignMode.CENTER, "PLAYED REMINDER");

            // stake details
            if(slipInfo != null) {
                setFontSpec(FONT_SIZE_NORMAL);
                Printer.getInstance().addText(0, formatAlignedJustified("COUPON CODE", slipInfo.coupon_code));
                Printer.getInstance().addText(0, formatAlignedJustified("SLIP ID", slipInfo.slipId));
                Printer.getInstance().addText(0, formatAlignedJustified("DATE", slipInfo.dateTime));

                Printer.getInstance().addText(AlignMode.CENTER, divider);
                setFontSpec(FONT_SIZE_LARGE);
                Printer.getInstance().addText(AlignMode.CENTER, slipInfo.bet_type);
            }

            if(selections != null) {
                setFontSpec(FONT_SIZE_NORMAL);
                // loop through selections to populate the printer slip
                for (Selection selection : selections) {
                    Printer.getInstance().addText(AlignMode.CENTER, divider);
                    Printer.getInstance().addText(AlignMode.LEFT, selection.getSport() + "-" + selection.getCategory());
                    Printer.getInstance().addText(AlignMode.LEFT, selection.getTournament());
                    Printer.getInstance().addText(AlignMode.LEFT, textToNewLine(selection.getEvent_id() + " - " + selection.getEvent()));
                    Printer.getInstance().addText(0, formatAlignedJustified(selection.getMarket_name(), "Odd: " + formatAmount(selection.getOdds(), false)));
                }
            }

            if(slipInfo != null) {
                // Add stake summary
                Printer.getInstance().addText(AlignMode.CENTER, single_divider);
                setFontSpec(FONT_SIZE_LARGE);
                Printer.getInstance().addText(AlignMode.CENTER, "SUMMARY");
                setFontSpec(FONT_SIZE_NORMAL);
                Printer.getInstance().addText(AlignMode.CENTER, single_divider);
                Printer.getInstance().addText(AlignMode.LEFT, formatAlignedJustified("BET", formatAmount(Double.parseDouble(slipInfo.totalStake), true)));
                Printer.getInstance().addText(AlignMode.LEFT, formatAlignedJustified("TOTAL ODD", formatAmount(Double.parseDouble(slipInfo.totalOdds), false)));
                Printer.getInstance().addText(AlignMode.CENTER, single_divider);
                Printer.getInstance().addText(AlignMode.CENTER, "BONUS");
                setFontSpec(FONT_SIZE_LARGE);
                Printer.getInstance().addText(AlignMode.CENTER, formatAmount(Double.parseDouble(slipInfo.bonus), true));
                setFontSpec(FONT_SIZE_NORMAL);
                Printer.getInstance().addText(AlignMode.CENTER, single_divider);
                Printer.getInstance().addText(AlignMode.CENTER, "POTENTIAL WINNINGS");
                setFontSpec(FONT_SIZE_LARGE);
                Printer.getInstance().addText(AlignMode.CENTER, formatAmount(Double.parseDouble(slipInfo.potentialWinnings), true));
                setFontSpec(FONT_SIZE_NORMAL);
            }
            // Add QR Code
//            Printer.getInstance().addQrCode(AlignMode.CENTER, 200, 1, slipInfo.slipId);
            // Add company details
            setFontSpec(FONT_SIZE_NORMAL);
            Printer.getInstance().addText(AlignMode.CENTER, divider);
            setFontSpec(FONT_SIZE_SMALL);
            Printer.getInstance().addText(AlignMode.CENTER, "PLAY RESPONSIBLY");
            Printer.getInstance().addText(AlignMode.CENTER, "The game is prohibited for persons under the\nage of 18");
            Printer.getInstance().addText(AlignMode.CENTER, "Kindly visit www.rukkabet.com");
            Printer.getInstance().addText(AlignMode.CENTER, "Phone: 08162576039 / 08154013511");
            Printer.getInstance().addText(AlignMode.CENTER, "Terms and conditions apply.");
            Printer.getInstance().addText(AlignMode.CENTER, "Do not play amounts in excess of your budget,");
            Printer.getInstance().addText(AlignMode.CENTER, "playing too much can cause an addiction.");
            Printer.getInstance().addText(AlignMode.CENTER, "Please see our rules: the account holder is\nsolely responsible for the printing of the");
            Printer.getInstance().addText(AlignMode.CENTER, "ticket, which is only transmitted");
            Printer.getInstance().addText(AlignMode.CENTER, " via the internet");

            // Add whitespace
            Printer.getInstance().feedLine(6);
            // Start printing
            Printer.getInstance().start(new OnPrintListener.Stub() {

                @Override
                public void onFinish() throws RemoteException {
                    Log.d("Print", "----- onFinish -----");

                            hideDialog();
//                    Toast.makeText(context.getApplicationContext(), R.string.succeed, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(int error) throws RemoteException {
                    Log.d("Print", "----- onError ----");

                            hideDialog();
//                    Toast.makeText(context.getApplicationContext(), Printer.getErrorId(error), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String textToNewLine(String str){
        String strArray[] = str.split("");
        String newStr = ""; int count = 0;
        for(int i=0; i < strArray.length; i++){
            newStr += strArray[i];
            if(count > 30){
                newStr += "\n";
                count = 0;
            }
            count = count+1;
        }
        return newStr;
    }

    private String formatAmount(double totalAuthAmount, boolean curSym) {
        String Currency = "NGN";
        String Separator = ",";
        Boolean Spacing = false;
        Boolean Delimiter = false;
        Boolean Decimals = true;
        String currencyFormat = "";
        if (Spacing) {
            if (Delimiter) {
                currencyFormat = ". ";
            } else {
                currencyFormat = " ";
            }
        } else if (Delimiter) {
            currencyFormat = ".";
        } else {
            currencyFormat = "";
        }
        if(curSym){
            currencyFormat = Currency+currencyFormat;
        }

        String tformatted = NumberFormat.getCurrencyInstance().format(totalAuthAmount / 1.0D).replace(NumberFormat.getCurrencyInstance().getCurrency().getSymbol(), currencyFormat);
        return tformatted;
    }


    /**
     * Hide dialog.
     */
    private void hideDialog() {
        progDialog.cancel();
    }

    private void showDialog(int resId, boolean cancelable) {
        progDialog.setMessage(context.getString(resId));
        progDialog.show();
        progDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(cancelable);
        if (progDialog.getWindow() != null) {
            // // TODO: 2017/9/8 屏蔽的Home的方法在 4.0 以后已经不再支持，在 C10  Android 7.0 上会报错。
            /** 为了更好地实现应用屏蔽HOME键的功能而又不引起异常，现已在P990及W280PV2的版本上新增了一个接口，
             该接口可以让应用的window屏蔽HOME键及APP_SWITCH键（就是调出近期应用的键），调用方法如下：

             @Override
             public void onAttachedToWindow() {
             super.onAttachedToWindow();
             Window win = getWindow();
             try {
             Class<?> cls = win.getClass();
             final Class<?>[] PARAM_TYPES = new Class[] {int.class};
             Method method = cls.getMethod("addCustomFlags", PARAM_TYPES);
             method.setAccessible(true);
             method.invoke(win, new Object[] {0x00000001});
             } catch (Exception e) {
             // handle the error here.
             }
             }

             该方法在2015.03.17号的烧片版本才有效。以后的应用屏蔽HOME键，请尽量使用此方法，不要再使用TYPE_KEYGUARD_DIALOG方式。
             **/
//            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        }
    }


    /**
     * Set font spec.
     */
    private void setFontSpec(int fontSpec) throws RemoteException {
        switch (fontSpec) {
            case FONT_SIZE_SMALL:
                Printer.getInstance().setHzSize(HZSize.DOT16x16);
                Printer.getInstance().setHzScale(HZScale.SC1x1);
                Printer.getInstance().setAscSize(ASCSize.DOT16x8);
                Printer.getInstance().setAscScale(ASCScale.SC1x1);
                break;

            case FONT_SIZE_NORMAL:
                Printer.getInstance().setHzSize(HZSize.DOT24x24);
                Printer.getInstance().setHzScale(HZScale.SC1x1);
                Printer.getInstance().setAscSize(ASCSize.DOT24x12);
                Printer.getInstance().setAscScale(ASCScale.SC1x1);
                break;

            case FONT_SIZE_LARGE:
                Printer.getInstance().setHzSize(HZSize.DOT24x24);
                Printer.getInstance().setHzScale(HZScale.SC1x2);
                Printer.getInstance().setAscSize(ASCSize.DOT24x12);
                Printer.getInstance().setAscScale(ASCScale.SC1x2);
                break;
        }
    }

    private byte[] readAssetsFile(String fileName) throws RemoteException {

        InputStream input = null;
        try {
            input = this.context.getAssets().open(fileName);
            byte[] buffer = new byte[input.available()];
            int size = input.read(buffer);
            if (size == -1) {
                throw new RemoteException(this.context.getString(R.string.read_fail));
            }
            return buffer;
        } catch (IOException e) {
            throw new RemoteException(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private static byte[] readAssetsFileStorage(String fileName) throws RemoteException {
        FileInputStream input = null;
        Object var5;
        try {
            File f = new File(fileName);
            if (f == null || !f.exists()) {
                Object var18 = null;
                return (byte[])var18;
            }

            input = new FileInputStream(f);
            byte[] buffer = new byte[input.available()];
            int size = input.read(buffer);
            if (size != -1) {
                byte[] var19 = buffer;
                return var19;
            }

            var5 = null;
        } catch (IOException var16) {
            throw new RemoteException(var16.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var15) {

                }
            }

        }

        return (byte[])var5;
    }


    private static String formatAlignedJustified(String left, String right) {
        if (left != null && right != null) {
            int leftlen = left.length();
            int rightlen = right.length();
            int space = 32 - (leftlen + rightlen);
            String sp = "";

            for(int i = 0; i < space; ++i) {
                sp = sp + " ";
            }

            return left + sp + right;
        } else {
            return "";
        }
    }

}
