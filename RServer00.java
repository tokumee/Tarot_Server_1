import java.io.*;           //ファイルの入出力用パッケージの宣言
import java.net.*;          //ネットワークアプリを実装するための宣言
import java.util.Random;    //Randomを使用するための呼び出し

public class RServer00{
    public static final int PORT=2525;
    public static void main(String[] args) {
        ServerSocket serverSocket=null;             //Socket型のserverSocketの定義、初期化
        Socket socket=null;                         //Socket型のsocketの定義、初期化
        try{
            serverSocket=new ServerSocket(PORT);                                                            //serverSocketを、PORTを引数にして作成
            System.out.println("タロットサーバーが起動しました(port=" + serverSocket.getLocalPort() + ")");   //起動確認
            socket=serverSocket.accept();                                                                    //acceptを使ってクライアントからの要求が来るまで待機
            System.out.println("タロットサーバーに接続されました" + socket.getRemoteSocketAddress());         //接続確認
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));          //インスタンス「in」をInputStreamReaderとgetInputStreamを使って作成
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);                      //インスタンス「out」をPrintWriterとgetOutputStreamを使って作成

            //Random型のrandomの作成
            Random random=new Random();
            //各カードの名前
            String[] cards={"愚者","魔術師","女教皇","女帝","皇帝","法王","恋人","戦車","力","隠者","運命の輪","正義","吊るされた男","死神","節制","悪魔","塔","星","月","太陽","審判","世界"};
            //各カードの意味や関連ワードなど
            String[] main={"思い立ったら始める姿勢が際立ち、自由や楽天的、何かを始めることを意味する。  無邪気、冒険家、自由、楽天的などなど。","創造性や自信、才能を意味し、自らの能力で新たなものを生み出していくだろう。  創造、自信、技術、才能などなど。",
            "神秘性と知性を兼ね備えた冷静な判断を意味し、頭脳を使う作業や勉強で、良い結果が出せることを表す。  知性、聡明、神秘、英知、判断などなど。","物質面と精神面の両方が満たされ、心から満足できることを意味する。  豊穣、満足、包容力、魅力、愛情などなど。",
            "これまでの努力が実り、成功を治める。  支配、権力、安定、成功、責任などなど。","慈悲や優しさを内に秘めた、人道的な判断を下すことを意味する。  慈悲、優しさ、法の順守、尊敬、寛大などなど。","恋愛初期のようなウキウキする、感情的なときめきを表し、その楽しさや喜びが強調される。  恋愛、情熱、結婚、ときめく心、調和などなど。",
            "迅速な行動や、強い意思で猛進することで勝利を得ることを意味する。  実行力、強い意思、迅速、勝利、征服などなど。","強い意思や理性で、自らの感情や本能を抑え込むという意味を持つ。  強い意思、自制、不屈、理性、力量などなど。",
            "内観としての要素が高まり、冷静で思慮深く物事を見極めることを意味する。  精神性、内観、悟り、思慮深い、慎重などなど。","一時的なチャンスや幸運に見舞われることを意味する。  一時的な幸運、変化、運命、出会いなどなど。",
            "公平な判断や平等であることを示し、公平な判断やバランスが取れるだろう。  公正な判断、均衡、正しさ、平等などなど。","自らを成長させるための試練の時が訪れることを意味する。  試練、修行、努力、苦労が報われるなどなど。",
            "停止や中止などの終わりを意味し、新たなる再生や始動のために、強制的に物事が終了する事実を告げている。  強制終了、中止、破局、終焉、停止などなど。","水が流れるかのように、自然と物事が整っていくことを意味する。  循環、順調、調和、管理、淀みないなどなど。",
            "悪魔からの甘い誘惑に心が捕らわれて、堕落や裏切りに染まってしまうことを意味する。  誘惑、堕落、裏切り、破滅、依存などなど","突然の災害や崩壊など、これまで築いたものが崩れ去るだろう。  災害、災難、事故、崩壊、ショックなどなど。",
            "理想や希望に満ちた心や、直感や閃きなどのインスピレーションを得ることを意味する。  直感、閃き、希望、憧れ、目標などなど。","過去のトラウマの影響や、心の中にストレスがあり不安定であることを意味する。  不安、憂鬱、中途半端、誤解、移ろいなどなど。",
            "成功や栄光、など手放しで喜べる状況を表す。  天真爛漫、無邪気、喜び、栄光、成功などなど。","あなたの行いが認められ、祝福されることを意味し、再生や再チャレンジの機会が与えられるだろう。  復活、祝福、再生、再チャレンジなどなど。",
            "自分の理想とする現実が訪れるだろう。  完成、理想郷、成功、充足、完璧などなど。"};
            String ten=".";
            String bye="さようなら.....";

            double h;
            h=Double.parseDouble(in.readLine());                //クライアントサーバーで入力されたhを受信し、hに入れる
            while(true){                                        //無限ループ
                if(h==1){
                    out.println(ten);                           //String tenをクライアントに送信
                    int r=random.nextInt(22);             //0～21までのランダムな数字を変数rに入れる
                    out.println(cards[r]);                      //cards[]のr番目にある文字列をクライアントに送信
                    out.println(main[r]);                       //main[]のr番目にある文字列をクライアントに送信
                    h=Double.parseDouble(in.readLine());
                }else{
                    out.println(String.valueOf(bye));           //String byeをクライアントに送信
                    break;                                      //無限ループを抜け出す
                }
            }
        } catch(IOException e){                                 //例外処理
            e.printStackTrace();
        }
    }
}