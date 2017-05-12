package download.api;

/**
 * Created by Haochen on 2017/3/16.
 * TODO:
 */
public class DownloadCallback {
    private OnCompleteListener onComplete;
    private OnFailListener onFail;

    public void callback(boolean success) {
        if (success) {
            if (onComplete != null) {
                onComplete.onComplete();
            }
        } else {
            if (onFail != null) {
                onFail.onFail();
            }
        }
    }

    public void setOnComplete(OnCompleteListener onComplete) {
        this.onComplete = onComplete;
    }

    public void setOnFail(OnFailListener onFail) {
        this.onFail = onFail;
    }
}
