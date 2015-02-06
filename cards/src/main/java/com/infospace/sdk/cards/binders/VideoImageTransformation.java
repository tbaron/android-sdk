package com.infospace.sdk.cards.binders;

import android.content.res.Resources;
import android.graphics.*;

import com.infospace.sdk.cards.R;
import com.squareup.picasso.Transformation;

public class VideoImageTransformation implements Transformation {
	private final Resources resources;

	public VideoImageTransformation(Resources resources) {
		this.resources = resources;
	}

	@Override
	public Bitmap transform(Bitmap source) {
		Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),
			source.getHeight(),
			source.getConfig());

		Canvas canvas = new Canvas(bitmap);

		canvas.drawPaint(getImageLayer(source));
		canvas.drawPath(
			getIndicatorPath(source.getWidth(), source.getHeight()),
			getIndicatorPaint()
		);

		source.recycle();

		return bitmap;
	}

	private Paint getImageLayer(Bitmap source) {
		Shader imageShader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		Shader gradientShader = getGradientShader(source);

		Paint paint = new Paint();

		paint.setAntiAlias(true);
		paint.setShader(new ComposeShader(
			imageShader,
			gradientShader,
			PorterDuff.Mode.DST_IN
		));

		return paint;
	}

	private Shader getGradientShader(Bitmap source) {
		int gradientTopSize = resources.getDimensionPixelSize(R.dimen.insp_card_video_gradient_top);
		int gradientBottomSize = resources.getDimensionPixelSize(R.dimen.insp_card_video_gradient_bottom);
		int gradientFadeSize = resources.getDimensionPixelSize(R.dimen.insp_card_video_gradient_fade);

		int transparent = 0x80000000;

		int bottom = source.getHeight() - 1 - gradientBottomSize;

		Shader[] shaders = {
			new LinearGradient(
				0, gradientTopSize,
				0, gradientTopSize + gradientFadeSize,
				transparent,
				Color.BLACK,
				Shader.TileMode.CLAMP),

			new LinearGradient(
				0, bottom - gradientFadeSize,
				0, bottom,
				Color.BLACK,
				transparent,
				Shader.TileMode.CLAMP)
		};

		return new ComposeShader(
			shaders[0],
			shaders[1],
			PorterDuff.Mode.DST_IN);
	}

	private Path getIndicatorPath(int boundsWidth, int boundsHeight) {
		float height = resources.getDimensionPixelSize(R.dimen.insp_card_video_indicator_height) / 2;
		float width = (float) (height * Math.sqrt(3)) / 2;

		PointF center = new PointF(boundsWidth / 2, boundsHeight / 2);

		PointF a = new PointF(center.x - width, center.y - height);
		PointF b = new PointF(center.x - width, center.y + height);
		PointF c = new PointF(center.x + width, center.y);

		Path path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(a.x, a.y);
		path.lineTo(b.x, b.y);
		path.lineTo(c.x, c.y);
		path.close();

		return path;
	}

	private Paint getIndicatorPaint() {
		int color = resources.getColor(R.color.insp_card_video_indicator);
		int corner = resources.getDimensionPixelSize(R.dimen.insp_card_video_indicator_corner);

		Paint paint = new Paint();

		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		paint.setPathEffect(new CornerPathEffect(corner));
		paint.setAntiAlias(true);

		return paint;
	}

	@Override
	public String key() {
		return "VideoImageTransformation";
	}
}
