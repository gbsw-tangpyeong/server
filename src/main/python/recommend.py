import requests
from sklearn.neighbors import NearestNeighbors
import numpy as np

KAKAO_API_KEY = '015a18693982c82d6ced4d41295c73a2'  # 본인의 Kakao API 키를 입력하세요.

def get_current_location_via_kakao():
    """
    카카오 API를 사용하여 현재 위치를 가져옵니다.
    """
    url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json"
    headers = {"Authorization": f"KakaoAK {KAKAO_API_KEY}"}
    try:
        response = requests.get("https://ipinfo.io/json")  # IP 기반 위치 정보
        if response.status_code == 200:
            data = response.json()
            lat, lng = map(float, data['loc'].split(','))  # IP 기반으로 위도, 경도 가져오기

            # 카카오 API로 추가 위치 정보 확인
            params = {"x": lng, "y": lat}
            kakao_response = requests.get(url, headers=headers, params=params)
            if kakao_response.status_code == 200:
                return lat, lng  # 카카오 API로 확인된 좌표
            else:
                print("카카오 API 에러:", kakao_response.status_code, kakao_response.text)
                return lat, lng  # IP 기반 위치로 대체
        else:
            print("IP 기반 위치 정보 에러:", response.status_code, response.text)
            return None, None
    except Exception as e:
        print("Exception occurred:", e)
        return None, None

def get_places_nearby(lat, lng, radius=1000, query='랜드마크'):
    url = "https://dapi.kakao.com/v2/local/search/keyword.json"
    headers = {"Authorization": f"KakaoAK {KAKAO_API_KEY}"}
    params = {
        "query": query,
        "y": lat,
        "x": lng,
        "radius": radius,
        "size": 10
    }
    
    response = requests.get(url, headers=headers, params=params)
    
    if response.status_code == 200:
        places = response.json().get('documents', [])
        return [(place['place_name'], float(place['y']), float(place['x'])) for place in places]
    else:
        print("Error:", response.status_code, response.text)
        return []

def recommend_route(user_lat, user_lng):
    """
    사용자의 위치와 주변 장소를 기반으로 추천 장소를 반환합니다.
    - user_lat, user_lng: 사용자 위치 (위도, 경도)
    - 반환값: 추천 장소 리스트 [(이름, 위도, 경도), ...]
    """
    nearby_places = get_places_nearby(user_lat, user_lng, query="공원")

    # 사용자 선호 위치 생성 (현재 위치 기준 자동화)
    user_preferences = np.array([
        [user_lat + 0.001, user_lng + 0.001],
        [user_lat - 0.001, user_lng - 0.001],
        [user_lat + 0.002, user_lng - 0.002],
    ])

    candidate_places = np.array([[place[1], place[2]] for place in nearby_places])

    if len(candidate_places) == 0:
        print("근처에 추천할 장소가 없습니다.")
        return []

    # KNN 모델 초기화 및 학습
    knn = NearestNeighbors(n_neighbors=1, algorithm='ball_tree')
    knn.fit(user_preferences)

    # 후보군에 대해 가장 가까운 기준점 찾기
    distances, indices = knn.kneighbors(candidate_places)

    seen_places = set()
    recommended_places = []

    for idx in indices.flatten():
        place = nearby_places[idx]
        if place[0] not in seen_places:
            recommended_places.append(place)
            seen_places.add(place[0])

    return recommended_places

# 현재 위치 가져오기
user_lat, user_lng = get_current_location_via_kakao()

if user_lat and user_lng:
    print(f"현재 위치: ({user_lat}, {user_lng})")
    recommended = recommend_route(user_lat, user_lng)
    print("Recommended Route:")
    for place in recommended:
        print(f"장소 이름: {place[0]}, 위치: ({place[1]}, {place[2]})")
else:
    print("현재 위치를 가져올 수 없습니다.")