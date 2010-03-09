/**
 * 
 */
package org.anddev.andengine.opengl.texture.source;

import java.io.IOException;

import org.anddev.andengine.util.Debug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Nicolas Gramlich
 * @since 12:07:52 - 09.03.2010
 */
public class AssetTextureSource implements ITextureSource {
	// ===========================================================
	// Constants
	// ===========================================================
	
	// ===========================================================
	// Fields
	// ===========================================================
	
	private final int mWidth;
	private final int mHeight;

	private final String mAssetPath;
	private final Context mContext;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public AssetTextureSource(final Context pContext, final String pAssetPath) {
		this.mContext = pContext;
		this.mAssetPath = pAssetPath;
		
		final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
		decodeOptions.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeStream(pContext.getAssets().open(pAssetPath), null, decodeOptions);
		} catch (IOException e) { 
			Debug.w("Texture asset not found, " + pAssetPath, e);
		}
		this.mWidth = decodeOptions.outWidth;
		this.mHeight = decodeOptions.outHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public int getHeight() {
		return this.mHeight;
	}

	@Override
	public int getWidth() {
		return this.mWidth;
	}
	
	@Override
	public Bitmap getBitmap() {
		try {
			return BitmapFactory.decodeStream(this.mContext.getAssets().open(this.mAssetPath));
		} catch (IOException e) {
			Debug.e("Failed loading Bitmap", e);
			return null;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}