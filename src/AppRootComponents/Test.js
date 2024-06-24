import { Text, View, Image } from 'react-native'
import React from 'react'

const Test = () => {
  return (
    <View>
      <Text>Test</Text>
      <Image style={{ width: 100, height: 100 }} source={require('../assets/images/youtube.png')} />
    </View>
  )
}

export default Test