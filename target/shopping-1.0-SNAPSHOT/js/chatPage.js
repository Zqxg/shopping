
// ѩ��Ʈ���ű�
let canvas = document.querySelector('#myCanvas');
let context = canvas.getContext('2d');
let W = window.innerWidth;
let h = window.innerHeight; // �˴�������ƴд����
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

// ҳ���Զ�ˢ�½ű�
setInterval(function(){
    location.reload();
}, 5000); // ����ˢ��ʱ������5s

// ��ҳ�������ɺ�ִ�л���ѩ��
window.onload = function() {
    draw();
    // ���û���ѩ���Ķ�ʱ��
    setInterval(draw, 30);
};
