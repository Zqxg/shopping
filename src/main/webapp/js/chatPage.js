
// 雪花飘动脚本
let canvas = document.querySelector('#myCanvas');
let context = canvas.getContext('2d');
let W = window.innerWidth;
let h = window.innerHeight; // 此处修正了拼写错误
canvas.width = W;
canvas.height = h;

let num = 30;
let snows = [];
for (let i = 0; i < num; i++) {
    snows.push({
        x: Math.random() * W,
        y: Math.random() * h,
        r: Math.random() * 10 + 1
    });
}

let move = () => {
    for (let i = 0; i < num; i++) {
        let snow = snows[i];
        snow.x += Math.random() * 2 + 1;
        snow.y += Math.random() * 2 + 1;
        if (snow.x > W) {
            snow.x = 0;
        }
        if (snow.y > h) {
            snow.y = 0;
        }
    }
};

let draw = () => {
    context.clearRect(0, 0, W, h);
    context.beginPath();
    context.fillStyle = 'rgb(255,255,255)';
    context.shadowColor = 'rgb(255,255,255)';
    context.shadowBlur = 10;

    for (let i = 0; i < num; i++) {
        let snow = snows[i];
        context.moveTo(snow.x, snow.y);
        context.arc(snow.x, snow.y, snow.r, 0, Math.PI * 2);
    }
    context.fill();
    context.closePath();
    move();
};

// 页面自动刷新脚本
setInterval(function(){
    location.reload();
}, 5000); // 设置刷新时间间隔，5s

// 在页面加载完成后执行绘制雪花
window.onload = function() {
    draw();
    // 设置绘制雪花的定时器
    setInterval(draw, 30);
};
