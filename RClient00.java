import java.io.*;                               //ファイルの入出力用パッケージの宣言
import java.net.*;                              //ネットワークアプリを実装するための宣言
import java.util.concurrent.TimeUnit;           //TimeUnitの宣言

public class RClient00{
    public static final int PORT=2525;          //ポート番号「2525」の指定
    public static void main(String[] args) {
        Socket socket=null;                     //Socket型のsocketの定義、初期化
        try{
            socket=new Socket(args[0],PORT);                                                                //socketのインスタンス作成
            System.out.println("タロットサーバー" + socket.getRemoteSocketAddress() + "に接続しました。");    //接続確認
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));          //インスタンス「in」をInputStreamReaderとgetInputStreamを使って作成
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);                      //インスタンス「out」をPrintWriterとgetOutputStreamを使って作成
            BufferedReader KBIn = new BufferedReader(new InputStreamReader(System.in));                      //インスタンス「KBIn」をBufferedReaderとInputStreamReaderを使って作成

            double h;
            System.out.println("占いは必要ですか？（yes-->1 , no-->0）");
            h=Double.parseDouble(KBIn.readLine());                           //キーボードで直接数字を入力させ、その数字をhに代入する
            out.println(h);                                                  //入力されたh変数をサーバーに送信
            while(true){                                                     //無限ループ
                if(h==1){
                    String ten=in.readLine();                                //サーバーから送られてきたtenを、String tenに入れる
                    for(int i=0;i<10;i++){                                   //送られてきた「・」を0.5秒遅らせながら1個ずつ、計10個出力するプログラム群
                        System.out.print(ten);
                        try{
                            TimeUnit.MILLISECONDS.sleep(500);        //0.5秒間隔をあける
                            } catch(InterruptedException e){                 //割り込み例外処理の処理
                                e.printStackTrace();
                            }
                        }

                        System.out.println();                                //見栄えのために改行プログラムを添える

                        String card=in.readLine();                           //サーバーから送られてきたcards[r]をcardに代入
                        System.out.println(card);                            //cardの出力

                        String main=in.readLine();                           //サーバーから送られてきたmain[r]をmainに代入
                        String[] mainArray=main.split("");             //mainの文字列を1字ずつに分解し、mainArray[]に1文字ずつ丁寧に代入
                        for(String m : mainArray){                           //String mに1文字ずつ、なくなるまで代入し、1文字ずつ出力するプログラム群
                            System.out.print(m);                             //ついさっき代入されたmを出力
                        try{
                            TimeUnit.MILLISECONDS.sleep(100);        //0.1秒待つ
                        } catch(InterruptedException e){                     //例外処理
                            e.printStackTrace();
                        }
                    }
                    System.out.println("\n占いの結果に満足しましたか？（yes-->0 , no-->1）");
                    h=Double.parseDouble(KBIn.readLine());                   //hに、入力された数字を代入
                    out.println(h);                                          //hをサーバーに送信
                }else{
                    String bye=in.readLine();                                //サーバーからのbyeをbyeに代入
                    System.out.println(bye);                                 //byeを出力
                    break;                                                   //無限ループから脱出
                }
            }
            out.close();                                                     //終了
            socket.close();
        } catch(IOException e){                                              //例外処理
            e.printStackTrace();
        }
    }
}