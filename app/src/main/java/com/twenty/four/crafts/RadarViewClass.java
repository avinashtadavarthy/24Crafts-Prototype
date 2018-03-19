package com.twenty.four.crafts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rakesh on 13/3/18.
 */

public class RadarViewClass extends View implements View.OnClickListener{

    String url = "http://24crafts.cf:3000/users/5a9e814e0a462e6e1f7fa9c5/photos/24 Logo.png";
    private Context mContext;
    private boolean isSearching = false;// 标识是否处于扫描状态,默认为不在扫描状态
    private Paint mPaint;// 画笔
    private Bitmap mScanBmp;// 执行扫描运动的图片
    private int mOffsetArgs = 0;// 扫描运动偏移量参数
    private Bitmap mDefaultPointBmp;// 标识设备的圆点-默认
    private Bitmap mLightPointBmp;// 标识设备的圆点-高亮
    private int mPointCount = 0;// 圆点总数
    private List<String> mPointArray = new ArrayList<>();// 存放偏移量的map
    private Random mRandom = new Random();
    private int mWidth, mHeight;// 宽高
    int mOutWidth;// 外圆宽度(w/4/5*2=w/10)
    int mCx, mCy;// x、y轴中心点
    int mOutsideRadius, mInsideRadius;// 外、内圆半径
    ArrayList<Integer> mPointArrayX,mPointArrayY;

    public RadarViewClass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public RadarViewClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public RadarViewClass(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    /**
     * TODO<提前初始化好需要使用的对象,避免在绘制过程中多次初始化>
     */
    private void init(Context context) {
        mPaint = new Paint();
        this.mContext = context;
        this.mDefaultPointBmp = Bitmap.createBitmap(BitmapFactory
                .decodeResource(mContext.getResources(),
                        R.drawable.radar_default_point_ico));
        this.mLightPointBmp = Bitmap.createBitmap(BitmapFactory.decodeResource(
                mContext.getResources(), R.drawable.taigamod2));
        this.mDefaultPointBmp = Bitmap.createScaledBitmap(mDefaultPointBmp,10,10,false);
        this.mLightPointBmp = Bitmap.createScaledBitmap(mLightPointBmp,150,150,false);
        this.mPointArrayX = new ArrayList<>();
        this.mPointArrayY = new ArrayList<>();

    }

    /**
     * 测量视图及其内容,以确定所测量的宽度和高度(测量获取控件尺寸).
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取控件区域宽高
        if (mWidth == 0 || mHeight == 0) {
            final int minimumWidth = getSuggestedMinimumWidth();
            final int minimumHeight = getSuggestedMinimumHeight();
            mWidth = resolveMeasured(widthMeasureSpec, minimumWidth);
            mHeight = resolveMeasured(heightMeasureSpec, minimumHeight);
            mScanBmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                    mContext.getResources(), R.drawable.radar_scan_img), mWidth
                    - mOutWidth, mWidth - mOutWidth, false);

            // 获取x/y轴中心点
            mCx = mWidth / 2;
            mCy = mHeight / 2;

            // 获取外圆宽度
            mOutWidth = mWidth / 10;

            // 计算内、外半径
            mOutsideRadius = mWidth / 2;// 外圆的半径
            mInsideRadius = (mWidth - mOutWidth) / 4 / 2;// 内圆的半径,除最外层,其它圆的半径=层数*insideRadius
        }
    }

    /**
     * 绘制视图--从外部向内部绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        // 开始绘制最外层的圆

        mPaint.setAntiAlias(true);// 设置抗锯齿
        mPaint.setStyle(Paint.Style.FILL);// 设置填充样式
        mPaint.setColor(0xffB8DCFC);// 设置画笔颜色
        // 1.开始绘制圆形
        canvas.drawCircle(mCx, mCy, mOutsideRadius, mPaint);

        // 开始绘制内4圆
        mPaint.setColor(0xff3278B4);
        canvas.drawCircle(mCx, mCy, mInsideRadius * 4, mPaint);

        // 开始绘制内3圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xff31C9F2);
        canvas.drawCircle(mCx, mCy, mInsideRadius * 3, mPaint);

        // 开始绘制内2圆
        canvas.drawCircle(mCx, mCy, mInsideRadius * 2, mPaint);

        // 开始绘制内1圆
        canvas.drawCircle(mCx, mCy, mInsideRadius, mPaint);

        // 2.开始绘制对角线
        canvas.drawLine(mOutWidth / 2, mCy, mWidth - mOutWidth / 2, mCy, mPaint);// 绘制0°~180°对角线
        canvas.drawLine(mCx, mHeight - mOutWidth / 2, mCx, mOutWidth / 2,
                mPaint);// 绘制90°~270°对角线

        // 根据角度绘制对角线
        int startX, startY, endX, endY;
        double radian;

        // 绘制45°~225°对角线
        // 计算开始位置x/y坐标点
        radian = Math.toRadians((double) 45);// 将角度转换为弧度
        startX = (int) (mCx + mInsideRadius * 4 * Math.cos(radian));// 通过圆心坐标、半径和当前角度计算当前圆周的某点横坐标
        startY = (int) (mCy + mInsideRadius * 4 * Math.sin(radian));// 通过圆心坐标、半径和当前角度计算当前圆周的某点纵坐标
        // 计算结束位置x/y坐标点
        radian = Math.toRadians((double) 45 + 180);
        endX = (int) (mCx + mInsideRadius * 4 * Math.cos(radian));
        endY = (int) (mCy + mInsideRadius * 4 * Math.sin(radian));
        canvas.drawLine(startX, startY, endX, endY, mPaint);

        // 绘制135°~315°对角线
        // 计算开始位置x/y坐标点
        radian = Math.toRadians((double) 135);
        startX = (int) (mCx + mInsideRadius * 4 * Math.cos(radian));
        startY = (int) (mCy + mInsideRadius * 4 * Math.sin(radian));
        // 计算结束位置x/y坐标点
        radian = Math.toRadians((double) 135 + 180);
        endX = (int) (mCx + mInsideRadius * 4 * Math.cos(radian));
        endY = (int) (mCy + mInsideRadius * 4 * Math.sin(radian));
        canvas.drawLine(startX, startY, endX, endY, mPaint);

        // 3.绘制扫描扇形图
        canvas.save();// 用来保存Canvas的状态.save之后，可以调用Canvas的平移、放缩、旋转、错切、裁剪等操作.

        if (isSearching) {// 判断是否处于扫描
            canvas.rotate(mOffsetArgs, mCx, mCy);// 绘制旋转角度,参数一：角度;参数二：x中心;参数三：y中心.
            canvas.drawBitmap(mScanBmp, mCx - mScanBmp.getWidth() / 2, mCy
                    - mScanBmp.getHeight() / 2, null);// 绘制Bitmap扫描图片效果
            mOffsetArgs += 3;
        } else {
            canvas.drawBitmap(mScanBmp, mCx - mScanBmp.getWidth() / 2, mCy
                    - mScanBmp.getHeight() / 2, null);
        }

        // 4.开始绘制动态点
        canvas.restore();// 用来恢复Canvas之前保存的状态.防止save后对Canvas执行的操作对后续的绘制有影响.

        if (mPointCount > 0) {// 当圆点总数>0时,进入下一层判断

            if (mPointCount > mPointArray.size()) {// 当圆点总数大于存储坐标点数目时,说明有增加,需要重新生成随机坐标点
                int mx = mInsideRadius + mRandom.nextInt(mInsideRadius * 6);
                int my = mInsideRadius + mRandom.nextInt(mInsideRadius * 6);
                mPointArray.add(mx + "/" + my);
                mPointArrayX.add(mx);
                mPointArrayY.add(my);
            }

            // 开始绘制坐标点
            Log.e("mpointarray",mPointArray.size()+"");
            for (int i = 0; i < mPointArray.size(); i++) {
                String[] result = mPointArray.get(i).split("/");
                int mx = Integer.parseInt(result[0]);
                int my = Integer.parseInt(result[1]);

                Log.e("mpointarray","Mpointarray[" + i + "] = " + mx + " " + my);

                // 开始绘制动态点
                if (i < mPointArray.size() - 1)
                    canvas.drawBitmap(mDefaultPointBmp,
                            Integer.parseInt(result[0]),
                            Integer.parseInt(result[1]), null);
                else
                    canvas.drawBitmap(mLightPointBmp,
                            Integer.parseInt(result[0]),
                            Integer.parseInt(result[1]), null);
            }
        }

        if (isSearching)
            this.invalidate();
    }

    /**
     * TODO<设置扫描状态>
     */
    public void setSearching(boolean status) {
        this.isSearching = status;
        this.invalidate();
    }

    /**
     * TODO<新增动态点>
     */
    public void addPoint() {
        mPointCount++;
        this.invalidate();
    }

    /**
     * TODO<解析获取控件宽高>
     *
     * @return int
     */
    private int resolveMeasured(int measureSpec, int desired) {
        int result;
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                result = desired;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize, desired);
                break;
            case MeasureSpec.EXACTLY:
            default:
                result = specSize;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                for(int i = 0;i<mPointArray.size();i++)
                {
                    if(x >= mPointArrayX.get(i) && x < (mPointArrayX.get(i) + mLightPointBmp.getWidth())
                            && y >= mPointArrayY.get(i) && y < (mPointArrayY.get(i) + mLightPointBmp.getHeight()))
                    {
                        Toast.makeText(mContext,x + " " + y,Toast.LENGTH_LONG).show();
                    }
                }
        }


        return true;
    }

    @Override
    public void onClick(View view) {

    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

        }
    }
}