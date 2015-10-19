from pydub import AudioSegment
from pydub.effects import *
import wave, warnings, os

print sys.argv

#Chunk the song
chunkSize = 24000
chunk1 = song[0:(1*chunkSize)]
chunk2 = song[(chunkSize+1):(chunkSize*2)]
chunk3 = song[(chunkSize * 2 + 1):(chunkSize*3)]
chunk4 = song[(chunkSize *3 + 1):(chunkSize*4)]
chunk5 = song[(chunkSize *4 +1):(chunkSize*5)]


def fadeIn(clip,value):
	increment = value / len(clip)
	volLvl = 0;

	for x in xrange(0,len(clip)):
		change = volLvl + increment
		clip[x] + volLvl
		volLvl + increment
	return clip

def slowDown(song,value):
	#Export a slice of the song to slow down
	song.export("slowSongBefore.wav", 'wav')
	#Open clip
	originalClip = wave.open("slowSongBefore.wav","r")

	slowClip = wave.open("SLOW.wav","w")
	slowClip.setparams(originalClip.getparams())

	originalFrames = originalClip.readframes(originalClip.getnframes())
	slowClip.setframerate(int(round(originalClip.getframerate() * value)))

	slowClip.writeframes(originalFrames)
	slowClip.close()

	changedSlowClip = AudioSegment.from_file("SLOW.wav")

	#Delete the unnecessary ones
	os.remove("slowSongBefore.wav")
	os.remove("SLOW.wav")
	return changedSlowClip


def speedUp(song,value):
	#Export a slice of the song to slow down
	song.export("speedSongBefore.wav", 'wav')
	#Open clip
	originalClip = wave.open("speedSongBefore.wav","r")

	fastClip = wave.open("FAST.wav","w")
	fastClip.setparams(originalClip.getparams())

	originalFrames = originalClip.readframes(originalClip.getnframes())
	fastClip.setframerate(int(round(originalClip.getframerate() * value)))

	fastClip.writeframes(originalFrames)
	fastClip.close()

	os.remove("speedSongBefore.wav")
	changedFastClip = AudioSegment.from_file("FAST.wav")
	os.remove("FAST.wav")
	return changedFastClip

def reverseSound(song):
	reversedClip = song[0:10000].reverse()
	#reversedClip.export("cheerleaderReversed.wav","wav")
	return reversedClip

def blendSounds(song1,song2):
	song1clipped = song1[0:10000]
	song2clipped = song2[0:10000]
	blendedSound = song1clipped.append(song2clipped,crossfade=1000)
	#blendedSound.export("blended.wav","wav")
	return blendedSound



#Apply operations to the chunks
chunk1Changed = fadeIn(chunk1,6)
chunk2Changed = slowDown(chunk2,0.5)
chunk3Changed = speedUp(chunk3, 1.25)
chunk4Changed = reverseSound(chunk4)
chunk5Changed = blendSounds(chunk5,AudioSegment.from_mp3("Diplo.mp3"))

#Create outputs to see that we actually did something
chunk1Changed.export("TEST1FADEIN.wav","wav")
chunk2Changed.export("TEST2SLOWDOWN.wav","wav")
chunk3Changed.export("TEST3SPEEDUP.wav","wav")
chunk4Changed.export("TEST4REVERSE.wav","wav")
chunk5Changed.export("TEST5BLEND.wav","wav")	

#Export the new song
newSong = chunk1Changed.append(chunk2Changed.append(chunk3Changed.append(chunk4Changed.append(chunk5Changed))))
newSong.export("MorninggSUNCHANGED.wav", 'wav')


# slowDown(clip1,0.5)
# speedUp(clip1,1.5)
# reverseSound(clip1)
# blendSounds(clip1,clip2)