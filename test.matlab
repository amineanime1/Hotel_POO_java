clc;
clear all;
f0 = 1;
fmax = 1*f0; 
fs = 1024*fmax; 
T0= 1/f0;
ts = 1/fs ; 
nfft = 1024;
tmax = ((nfft/2)-1)*ts;
tmin = (-(nfft/2))*ts;
t= -20*T0:ts:+20*T0;
x= 1*cos(10*2*pi*f0*t-pi/2);
m = max(x);
figure(1)
plot(t,x,'b','LineWidth',2);
hold on;
xlabel('Temps')
ylabel('Amplitude ')
grid on
axis([-1*T0 +1*T0 -m-0.5 m+0.5])
[mod,phase,f] = specsig(x,t,ts); 
figure (2)
subplot(2,1,1)
plot(f,mod,'blue','LineWidth',2) 
xlabel('Fréquence')
ylabel('Amplitude ')
title('Spectre Bilatéral amplitude du signal')
grid on
axis([ -fs*(length(t)/128)/length(t) fs*(length(t)/128-1)/length(t) 0 3+0.5])
subplot(2,1,2)
plot(f,phase/1,'blue','LineWidth',2) 
xlabel('Fréquence')
ylabel('Phase ')
title('Spectre Bilatéral phase du signal')
axis([ -fs*(length(t)/128)/length(t) fs*(length(t)/128-1)/length(t) -pi-1 +pi+1])
grid on