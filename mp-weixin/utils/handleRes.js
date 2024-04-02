export const handleRes = (
    { status, message },
    success,
    delayFun,
    error,
    errorDelayFun
) => {
    if (status && status == 200) {
        handle(message, "success", success, delayFun);
    } else {
        handle(message, "none", error, errorDelayFun);
    }
};
const handle = (message, icon, callback, delayFun) => {
    callback && callback();
    const duration = delayFun ? 1000 : 1500;
    if (message) {
        if (message.length > 7) {
            wx.showToast({
                title: message,
                icon: "none",
                duration,
            });
        } else {
            wx.showToast({
                title: message,
                icon,
                duration,
            });
        }

    }
    if (delayFun) {
        setTimeout(() => {
            delayFun && delayFun();
        }, duration);
    }
};